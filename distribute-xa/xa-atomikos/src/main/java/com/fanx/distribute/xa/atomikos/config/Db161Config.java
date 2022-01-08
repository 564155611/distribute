package com.fanx.distribute.xa.atomikos.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = {"com.fanx.distribute.xa.mapper.db161"},sqlSessionFactoryRef = "sqlSessionFactoryBean161")
public class Db161Config {
    @Bean
    DataSource db161(){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUser("root");
        mysqlXADataSource.setPassword("123456");
        mysqlXADataSource.setUrl("jdbc:mysql://192.168.12.161:3306/xa_161");
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("mysql/xa_161");
        return atomikosDataSourceBean;
    }

    @Bean("sqlSessionFactoryBean161")
    SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("db161") DataSource db161) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db161);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mybatis/db161/*.xml"));
        return sqlSessionFactoryBean;
    }
}
