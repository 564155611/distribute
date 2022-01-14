package com.fanx.distribute.eventualconsistency.mq.service;

import com.fanx.distribute.eventualconsistency.mq.entity.Order;
import com.fanx.distribute.eventualconsistency.mq.mapper.db162.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    /**
     * @param orderId 订单业务系统中的订单id
     * @return 1:订单不存在
     */
    @Transactional(transactionManager = "tm162")
    public int orderCallback(Integer orderId){
        if (orderId == null) {
            return 1;
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            return 1;
        }
        order.setStatus(1);
        order.setUpdateTime(new Date());
        order.setUpdateUser(0);//系统更新
        orderMapper.updateByPrimaryKeySelective(order);
        return 0;
    }
    @Transactional(transactionManager = "tm162")
    public int createOrder(Integer userId, BigDecimal amount){
        if (userId == null || amount == null) {
            return 1;
        }
        Order order = new Order();
        order.setId(10010);
        order.setStatus(0);
        order.setAmount(amount);
        order.setReceiveUser("张三");
        order.setReceiveMobile("13311112222");
        order.setCreateUser(userId);
        order.setCreateTime(new Date());
        order.setUpdateUser(userId);
        order.setUpdateTime(new Date());
        orderMapper.insert(order);
        return 0;
    }

}

