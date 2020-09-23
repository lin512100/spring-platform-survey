package com.jtang.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 默认数据源配置
 * @author lin512100
 * @date 2020/9/23
 */
@Configuration
@MapperScan(value ="com.jtang.mapper" ,sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class DefaultDataSource {

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    @Bean(name = "teacherDataSource")
    public DataSource masterDataSource() {
        DruidXADataSource xaDataSource = new DruidXADataSource();
        xaDataSource.setUrl("jdbc:mysql://localhost:3306/reactstu?useUnicode=true&characterEncoding=UTF8&useSSL=false");
        xaDataSource.setUsername("root");
        xaDataSource.setPassword("");

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setUniqueResourceName("teacherDataSource");
        atomikosDataSourceBean.setPoolSize(5);
        return atomikosDataSourceBean;
    }


    @Bean(name = "teacherSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("teacherDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        return sessionFactory.getObject();
    }

}