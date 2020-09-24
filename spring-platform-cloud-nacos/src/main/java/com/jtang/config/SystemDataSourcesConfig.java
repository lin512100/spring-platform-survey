package com.jtang.config;


import com.jtang.web.utils.DataSourceUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 系统测试数据源
 * @author lin512100
 * @date 2020/9/25
 */
@Configuration
@MapperScan(basePackages = "com.jtang.system.mapper",
        sqlSessionFactoryRef = "systemSqlSessionFactory")
public class SystemDataSourcesConfig {

    public static final String DATABASE_PREFIX = "spring.datasource.system.";

    public static final String DATA_SOURCE_NAME = "systemDataSource";
    public static final String SQL_SESSION_FACTORY = "systemSqlSessionFactory";

    /**
     * 通过配置文件创建DataSource，一个数据库对应一个DataSource
     * @param environment 环境变量，spring-boot会自动将IOC中的environment实例设置给本参数值
     * 由于IOC中有多个DataSource实例，必须给其中一个实例加上@Primary
     */
    @Bean(DATA_SOURCE_NAME)
    public DataSource dataSource(Environment environment) {
        return DataSourceUtil.createAtomikosDataSourceBean(DATA_SOURCE_NAME, environment, DATABASE_PREFIX);
    }

    /**
     * 通过dataSource创建SqlSessionFactory
     * 由于IOC中有多个DataSource实例，必须给其中一个实例加上@Primary
     */
    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        return DataSourceUtil.createSqlSessionFactory(dataSource);
    }

}
