package com.dcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcc.domain.Goods;
import com.dcc.mapper.GoodsMapper;
import com.dcc.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {}
