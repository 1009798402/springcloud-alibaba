package com.dcc.config.exception;

import com.dcc.config.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author jianchun.chen
 * @date 2021/7/4 12:32
 *     <p>-----
 */
@Slf4j
@Order(-10000)
@Component
@RequiredArgsConstructor
public class DccGlobalExceptionHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

    ServerHttpResponse response = exchange.getResponse();

    if (response.isCommitted()) {
      return Mono.error(ex);
    }

    // header set
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    if (ex instanceof ResponseStatusException) {
      response.setStatusCode(((ResponseStatusException) ex).getStatus());
    }

    return response.writeWith(
        Mono.fromSupplier(
            () -> {
              DataBufferFactory bufferFactory = response.bufferFactory();
              try {
                return bufferFactory.wrap(
                    objectMapper.writeValueAsBytes(Result.error(ex.getMessage())));
              } catch (JsonProcessingException e) {
                log.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
              }
            }));
  }
}
