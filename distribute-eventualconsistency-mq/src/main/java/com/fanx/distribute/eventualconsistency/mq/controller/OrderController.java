package com.fanx.distribute.eventualconsistency.mq.controller;

import com.fanx.distribute.eventualconsistency.mq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PutMapping(value="/order")
    public String order(@RequestParam("userId")Integer userId, @RequestParam("amount")BigDecimal amount) {
        int result = orderService.createOrder(userId, amount);
        return "下单: " + result;
    }

    @PostMapping(value="/ordercallback")
    public String orderCallback(@RequestParam("orderId") Integer orderId) {
        try {
            int result = orderService.orderCallback(orderId);
            return result == 0 ? "success" : "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
