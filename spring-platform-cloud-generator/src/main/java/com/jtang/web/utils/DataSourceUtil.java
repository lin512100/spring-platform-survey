package com.jtang.web.utils;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

/**
 * 数据源配置工具类
 * @author lin512100
 * @date 2020/9/23
 */
public class DataSourceUtil {
    public static final String DATA_SOURCE_PREFIX = "spring.datasource.";

    /**
     * 创建AtomikosDataSourceBean是使用Atomikos连接池的首选类
     */
    public static AtomikosDataSourceBean createAtomikosDataSourceBean(String uniqueResourceName, Environment environment, String dataBase ){
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        // 这些设置大家可以进入源码中看java-doc
        // 数据源唯一标识
        atomikosDataSourceBean.setUniqueResourceName(uniqueResourceName);
        // XADataSource实现类，使用DruidXADataSource
        atomikosDataSourceBean.setXaDataSourceClassName(environment.getProperty(DATA_SOURCE_PREFIX+"type"));
        // 最小连接数，默认1
        atomikosDataSourceBean.setMinPoolSize(Integer.parseInt(environment.getProperty(DATA_SOURCE_PREFIX+"min-pool-size","1")));
        // 最大连接数，默认1
        atomikosDataSourceBean.setMaxPoolSize(Integer.parseInt(environment.getProperty(DATA_SOURCE_PREFIX+"max-pool-size", "1")));
        // 设置连接在池中被自动销毁之前保留的最大秒数。 可选，默认为0（无限制）。
        atomikosDataSourceBean.setMaxLifetime(Integer.parseInt(environment.getProperty(DATA_SOURCE_PREFIX+"max-life-time", "0")));
        // 返回连接前用于测试连接的SQL查询
        atomikosDataSourceBean.setTestQuery(environment.getProperty(DATA_SOURCE_PREFIX+"test-query"));

        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setDatabaseName(environment.getProperty(dataBase+"name"));
        mysqlXADataSource.setURL(environment.getProperty(dataBase+"url"));
        mysqlXADataSource.setUser(environment.getProperty(dataBase+"username"));
        mysqlXADataSource.setPassword(environment.getProperty(dataBase+"password"));
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);

        return atomikosDataSourceBean;
    }

    /**
     * 创建SqlSessionFactory实例
     */
    public static SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception{
        /**
         * 必须使用MybatisSqlSessionFactoryBean，
         * 不能使用SqlSessionFactoryBean，不然会报invalid bound statement (not found)
         *
         * com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#sqlSessionFactory(javax.sql.DataSource)
         * 源码中也是使用MybatisSqlSessionFactoryBean
         * 并且源码中使用了@ConditionalOnMissingBean，即IOC中如果存在了SqlSessionFactory实例，mybatis-plus就不创建SqlSessionFactory实例了
         */
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }

}
