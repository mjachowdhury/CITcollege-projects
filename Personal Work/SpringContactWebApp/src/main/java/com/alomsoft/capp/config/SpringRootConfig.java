 
package com.alomsoft.capp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.managed.BasicManagedDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Mohammed
 */

@Configuration
@ComponentScan(basePackages = {"com.alomsoft.capp.dao","com.alomsoft.capp.service"})


public class SpringRootConfig {
    //TODO: services,dto,dao,dataSource, email sender etc or some other business layer beans
    
    @Bean
    public BasicDataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/capp_db");
        ds.setUsername("root");
        ds.setPassword("mysql_2018");
        
        ds.setMaxTotal(2);
        ds.setInitialSize(1);
        ds.setTestOnBorrow(true);
        ds.setValidationQuery("SELECT 1");
        ds.setDefaultAutoCommit(true);
        return ds;
        
    }
}
