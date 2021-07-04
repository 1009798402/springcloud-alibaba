package com.dcc.filters.global;

import com.alibaba.fastjson.JSONObject;
import com.dcc.config.Result;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author jianchun.chen
 * @date 2021/7/4 11:44
 *     <p>-----
 */
@Slf4j
@Component
public class ResponseFormatGlobalFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    ServerHttpResponse originalResponse = exchange.getResponse();
    DataBufferFactory bufferFactory = originalResponse.bufferFactory();
    ServerHttpResponseDecorator decoratedResponse =
        new ServerHttpResponseDecorator(originalResponse) {
          @Override
          public @NotNull Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
            if (body instanceof Flux) {
              Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
              return super.writeWith(
                  fluxBody.map(
                      dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        // 释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String data = new String(content, StandardCharsets.UTF_8);

                        HttpStatus responseCode = originalResponse.getStatusCode();
                        String result;
                        if (responseCode != null && responseCode.is2xxSuccessful()) {
                          result = JSONObject.toJSONString(Result.ok(data));
                        } else {
                          result =
                              JSONObject.toJSONString(
                                  Result.error(new String(content, StandardCharsets.UTF_8)));
                        }

                        originalResponse.getHeaders().setContentLength(result.getBytes().length);
                        originalResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        return bufferFactory.wrap(result.getBytes());
                      }));
            }
            // if body is not a flux. never got there.
            return super.writeWith(body);
          }
        };
    // replace response with decorator
    return chain.filter(exchange.mutate().response(decoratedResponse).build());
  }

  @Override
  public int getOrder() {
    return -2;
  }
}
