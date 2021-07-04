package com.dcc.config;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/7/4 12:42
 *     <p>-----
 */
@Data
public class Result<T> {

  private boolean success;

  private T data;

  private String error;

  private LocalDateTime timestamp;

  public static <T> Result<T> ok(T data) {
    Result<T> result = new Result<>();
    result.setSuccess(true);
    result.setData(data);
    result.setTimestamp(LocalDateTime.now());
    return result;
  }

  public static <T> Result<T> error(String error) {
    Result<T> result = new Result<>();

    result.setSuccess(false);
    result.setError(error);
    result.setTimestamp(LocalDateTime.now());
    return result;
  }
}
