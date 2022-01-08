package com.fanx.distribute.xa.shardingspheredemo;

import com.fanx.distribute.xa.shardingspheredemo.entity.Order;
import com.fanx.distribute.xa.shardingspheredemo.entity.OrderItem;
import com.fanx.distribute.xa.shardingspheredemo.mapper.OrderItemMapper;
import com.fanx.distribute.xa.shardingspheredemo.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan(basePackages = {"com.fanx.distribute.xa.shardingspheredemo.mapper"})
@Slf4j
public class ShardingSphereDemoApplicationTests {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Test
    public void clear() {
        orderMapper.deleteByExample(null);
        orderItemMapper.deleteByExample(null);
    }

    @Test
    @Transactional
    public void testOrderInsert() {
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setUserId(1);
        order1.setTotalAmount(new BigDecimal("100.0"));
        orderMapper.insertSelective(order1);

        log.info("order1主键:{}",order1.getOrderId());

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setUserId(2);
        order2.setTotalAmount(new BigDecimal("200.0"));
        orderMapper.insertSelective(order2);
        log.info("order2主键:{}",order2.getOrderId());

        Order order3 = new Order();
        order3.setOrderId(3L);
        order3.setUserId(3);
        order3.setTotalAmount(new BigDecimal("300.0"));
        orderMapper.insertSelective(order3);
        log.info("order3主键:{}",order3.getOrderId());

        throw new RuntimeException("XA exception");
    }

    @Test
    public void testOrderItemInsert() {
        OrderItem orderItem1 = new OrderItem();
//        orderItem1.setId(1);
        orderItem1.setOrderId(1L);
        orderItem1.setUserId(1);
        orderItem1.setProductId(1);
        orderItem1.setProductNum(1);
        orderItem1.setSoldPrice(new BigDecimal("100.0"));
        orderItemMapper.insertSelective(orderItem1);
        log.info("orderItem1主键:{}",orderItem1.getId());

        OrderItem orderItem2 = new OrderItem();
//        orderItem2.setId(2);
//        orderItem2.setOrderId(2);
        orderItem2.setUserId(2);
        orderItem2.setProductId(2);
        orderItem2.setProductNum(1);
        orderItem2.setSoldPrice(new BigDecimal("200.0"));
        orderItemMapper.insertSelective(orderItem2);
        log.info("orderItem2主键:{}",orderItem2.getId());

        OrderItem orderItem3 = new OrderItem();
//        orderItem3.setId(3);
//        orderItem3.setOrderId(3);
        orderItem3.setUserId(3);
        orderItem3.setProductId(3);
        orderItem3.setProductNum(1);
        orderItem3.setSoldPrice(new BigDecimal("300.0"));
        orderItemMapper.insertSelective(orderItem3);
        log.info("orderItem3主键:{}",orderItem3.getId());

    }

    @Test
    public void testSelectJoin() {
        List<Map<String, Object>> mapList = orderItemMapper.selectOrderJoinItemsByOrderId(1L);
        log.info("mapList.size()==>{}",mapList.size());
        mapList.forEach(map -> {
            log.info("=================================");
            map.forEach((k, v) -> {
                log.info("键{}={}值", k, v);
            });
        });
    }


}
