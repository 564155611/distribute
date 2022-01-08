package com.fanx.distribute.tcc.template;

import com.fanx.distribute.tcc.template.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TccTemplateApplicationTests {
    @Autowired
    ClientService clientService;
    @Test
    public void test(){
        clientService.execute();
    }
}
