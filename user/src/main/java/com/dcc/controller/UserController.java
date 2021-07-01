package com.dcc.controller;

import com.dcc.domain.User;
import com.dcc.domain.dto.UserDto;
import com.dcc.service.UserService;
import com.dcc.util.DomainHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public User getUser(@PathVariable long id) {
    return userService.getById(id);
  }

  @PostMapping
  public void save(@RequestBody UserDto userDto) {
    userService.save(DomainHelper.dto2Entity(userDto, User.class));
  }
}
