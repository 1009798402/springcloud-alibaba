package com.dcc.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jianchun.chen
 * @date 2021/7/3 17:16
 *     <p>-----
 */
@Component
public class SentinelExceptionHandler implements BlockExceptionHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e)
      throws Exception {

    response.setContentType("application/json");
    if (e instanceof FlowException) {
      response.getWriter().write("我被限流了");
    } else if (e instanceof DegradeException) {
      response.getWriter().write("我被降级了");
    }
  }
}
