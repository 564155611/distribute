package com.fanx.distribute.eventualconsistency.localmsg.controller;

import com.fanx.distribute.eventualconsistency.localmsg.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(value="/pay")
    public String pay(@RequestParam("userId")Integer userId, @RequestParam("orderId")Integer orderId, @RequestParam("amount")BigDecimal amount){
        int result = paymentService.pay(userId, orderId, amount);
        return "支付结果: "+result;
    }
}
