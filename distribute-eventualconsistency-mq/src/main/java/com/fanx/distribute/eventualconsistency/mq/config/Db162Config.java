package com.fanx.distribute.eventualconsistency.mq.config;

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
@MapperScan(basePackages = {"com.fanx.distribute.eventualconsistency.mq.mapper.db162"}, sqlSessionFactoryRef = "sqlSessionFactoryBean162")
public class Db162Config {
    @Bean
    DataSource db162() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        mysqlDataSource.setUrl("jdbc:mysql://192.168.12.162:3306/xa_162");
        return mysqlDataSource;
    }

    @Bean("sqlSessionFactoryBean162")
    SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("db162") DataSource db162) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db162);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mybatis/db162/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean("tm162")
    PlatformTransactionManager transactionManager(@Qualifier("db162") DataSource db162) {
        return new DataSourceTransactionManager(db162);
    }
}
