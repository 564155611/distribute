package com.fanx.distribute.xa.mycat;

import com.fanx.distribute.xa.mycat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XaMycatApplicationTests {
    @Autowired
    UserService userService;
    @Test
    public void test(){
        userService.test();
    }
}
