package com.fanx.distribute.lock.oversell.service.impl;

import com.fanx.distribute.lock.oversell.entity.OrderItem;
import com.fanx.distribute.lock.oversell.mapper.OrderItemMapper;
import com.fanx.distribute.lock.oversell.service.IOrderItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表  服务实现类
 * </p>
 *
 * @author fanx
 * @since 2021-12-08
 */
@Service
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
