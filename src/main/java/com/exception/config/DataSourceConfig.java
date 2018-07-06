package com.exception.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 */
@Configuration
@MapperScan(basePackages = "com.enn.mcp.**.mapper.**", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {

//    @ApolloConfig
//    private Config config;



    @Bean(name = "dataSource")
    public DataSource dataSource() {
        String url = config.getProperty("datasource.url","");
        String user = config.getProperty("datasource.username","");
        String password = config.getProperty("datasource.password","");
        String driverClass = config.getProperty("datasource.driverClassName","");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));

        PagingInterceptor pagingInterceptor = new PagingInterceptor();
        Properties props = new Properties();
        props.setProperty("default.page", config.getProperty("default.page","1"));
        props.setProperty("default.pageSize", config.getProperty("default.pageSize","10"));
        props.setProperty("default.useFlag", config.getProperty("default.useFlag","true"));
        pagingInterceptor.setProperties(props);
        factory.setPlugins(new Interceptor[]{pagingInterceptor});

        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}