package com.fanx.distribute.xa.atomikos;

import com.fanx.distribute.xa.atomikos.service.XaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XaDemoApplicationTests {
    @Autowired
    XaService service;
    @Test
    public void testXa(){
        service.testXa();
    }
}
