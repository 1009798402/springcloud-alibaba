package com.dcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcc.domain.User;
import com.dcc.mapper.UserMapper;
import com.dcc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {}
