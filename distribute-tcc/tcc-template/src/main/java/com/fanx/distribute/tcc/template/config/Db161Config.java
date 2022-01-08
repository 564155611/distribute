package com.fanx.distribute.tcc.template.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = {"com.fanx.distribute.tcc.template.mapper.db161"},sqlSessionFactoryRef = "sqlSessionFactoryBean161")
public class Db161Config {
    @Bean
    DataSource db161(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        mysqlDataSource.setUrl("jdbc:mysql://192.168.12.161:3306/xa_161");
        return mysqlDataSource;
    }

    @Bean("sqlSessionFactoryBean161")
    SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("db161") DataSource db161) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db161);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mybatis/db161/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean("tm161")
    PlatformTransactionManager transactionManager(@Qualifier("db161") DataSource db161){
        return new DataSourceTransactionManager(db161);
    }
}
