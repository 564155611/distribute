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
@MapperScan(basePackages = {"com.fanx.distribute.xa.mapper.db162"},sqlSessionFactoryRef = "sqlSessionFactoryBean162")
public class Db162Config {
    @Bean
    DataSource db162(){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUser("root");
        mysqlXADataSource.setPassword("123456");
        mysqlXADataSource.setUrl("jdbc:mysql://192.168.12.162:3306/xa_162");
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("mysql/xa_162");
        return atomikosDataSourceBean;
    }

    @Bean("sqlSessionFactoryBean162")
    SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("db162") DataSource db162) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db162);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mybatis/db162/*.xml"));
        return sqlSessionFactoryBean;
    }
}
