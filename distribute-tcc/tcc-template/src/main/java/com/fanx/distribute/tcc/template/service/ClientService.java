package com.fanx.distribute.tcc.template.service;

import com.fanx.distribute.tcc.template.BusinessException;
import com.fanx.distribute.tcc.template.TccTemplate;
import com.fanx.distribute.tcc.template.entity.AccountA;
import com.fanx.distribute.tcc.template.entity.AccountB;
import com.fanx.distribute.tcc.template.mapper.db161.AccountAMapper;
import com.fanx.distribute.tcc.template.mapper.db162.AccountBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ClientService {
    @Autowired
    AccountAMapper accountAMapper;
    @Autowired
    AccountBMapper accountBMapper;

    @Transactional(transactionManager = "tm161", rollbackFor = Exception.class)
    public void execute() {
        Map<String, Boolean> resultMap = new HashMap<>();

        TccTemplate.process(v -> {
            //扣减accountA的100元
//            int a = 1 / 0;
            AccountA accountA = accountAMapper.selectByPrimaryKey(1);
            accountA.setBalance(accountA.getBalance().subtract(new BigDecimal("100")));
            accountAMapper.updateByPrimaryKey(accountA);
            resultMap.put("accountA", true);
            int a = 1 / 0;
            AccountB accountB = accountBMapper.selectByPrimaryKey(1);
            accountB.setBalance(accountB.getBalance().add(new BigDecimal("100")));
            accountBMapper.updateByPrimaryKey(accountB);
//            int a = 1 / 0;
            resultMap.put("accountB", true);
        }, () -> resultMap.values().stream().allMatch(value -> value), v -> {
            resultMap.forEach((key, flag) -> {
                if (flag) {
                    System.out.println(key + "账户操作需要回滚");
                    switch (key) {
                        case "accountA":
                            /*由于AccountA被@Transactional中的tm161管控,所以这里可以不用进行手动回滚,直接抛出异常即可*/
                            /*AccountA accountA = accountAMapper.selectByPrimaryKey(1);
                            accountA.setBalance(accountA.getBalance().add(new BigDecimal("100")));
                            accountAMapper.updateByPrimaryKey(accountA);*/
                            if (v != null) {
                                throw v;
                            }else{
                                throw new BusinessException("555", "accountA操作出现未知异常");
                            }
                        case "accountB":
                            AccountB accountB = accountBMapper.selectByPrimaryKey(1);
                            accountB.setBalance(accountB.getBalance().subtract(new BigDecimal("100")));
                            accountBMapper.updateByPrimaryKey(accountB);
                            break;
                        default:
                            break;
                    }
                }
            });
        }, "ClientDemo.execute");
    }
}
