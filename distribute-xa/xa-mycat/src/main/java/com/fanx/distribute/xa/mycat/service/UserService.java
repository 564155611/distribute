package com.fanx.distribute.xa.mycat.service;

import com.fanx.distribute.xa.mycat.entity.User;
import com.fanx.distribute.xa.mycat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void test(){
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("张三");
        userMapper.insert(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("李四");
        userMapper.insert(user2);
        User user3 = new User();
        user3.setId(3);
        user3.setUsername("王二麻子");
        userMapper.insert(user3);
    }
}
