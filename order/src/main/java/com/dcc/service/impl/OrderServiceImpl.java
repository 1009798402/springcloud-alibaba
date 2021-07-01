package com.dcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcc.domain.Order;
import com.dcc.mapper.OrderMapper;
import com.dcc.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * com.dcc.order 服务实现类
 *
 * @author jianchun.chen
 * @since 2021-07-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {}
