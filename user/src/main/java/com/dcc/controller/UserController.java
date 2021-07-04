package com.dcc.controller;

import com.dcc.domain.User;
import com.dcc.domain.dto.UserDto;
import com.dcc.service.UserService;
import com.dcc.util.DomainHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Slf4j
@RequestMapping("/f")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/test")
  public void test() {
    log.info("{} user test", LocalDateTime.now());
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable long id) {
    return userService.getById(id);
  }

  @PostMapping
  public void save(@RequestBody UserDto userDto) {
    userService.save(DomainHelper.dto2Entity(userDto, User.class));
  }
}
