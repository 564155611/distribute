package com.fanx.distribute.xa.atomikos.service;

import com.fanx.distribute.xa.atomikos.entity.Xa161;
import com.fanx.distribute.xa.atomikos.entity.Xa162;
import com.fanx.distribute.xa.atomikos.mapper.db161.Xa161Mapper;
import com.fanx.distribute.xa.atomikos.mapper.db162.Xa162Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class XaService {
    @Autowired
    Xa161Mapper xa161Mapper;
    @Autowired
    Xa162Mapper xa162Mapper;
    @Transactional(transactionManager = "xaTransaction",rollbackFor = Exception.class)
    public void testXa() {
        Xa161 xa161 = new Xa161();
        xa161.setId(1);
        xa161.setName("xa161");
        xa161Mapper.insert(xa161);

        Xa162 xa162 = new Xa162();
        xa162.setId(1);
        xa162.setName("xa162");
        xa162Mapper.insert(xa162);
    }
}
