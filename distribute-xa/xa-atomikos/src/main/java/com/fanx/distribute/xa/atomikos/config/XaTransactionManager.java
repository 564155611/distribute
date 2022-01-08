package com.fanx.distribute.xa.atomikos.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
public class XaTransactionManager {
    @Bean("xaTransaction")
    JtaTransactionManager jtaTransactionManager(){
        UserTransaction userTransaction = new UserTransactionImp();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(userTransaction,userTransactionManager);
        return jtaTransactionManager;
    }
}
