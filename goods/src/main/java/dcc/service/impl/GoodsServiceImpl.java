package dcc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcc.domain.Goods;
import dcc.mapper.GoodsMapper;
import dcc.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {}
