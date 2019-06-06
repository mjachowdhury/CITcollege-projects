  
package com.alomsoft.capp.config;

import javax.activation.DataSource;
import org.apache.commons.dbcp2.BasicDataSource; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 

/**
 *
 * @author Mohammed
 */
@Configuration
//@ComponentScan(basePackages = {"com.alomsoft.capp.dao"})
public class SpringRootConfig {
    //TODO : Services, DAO, DataSource, Email sender etc or some other business layer beans
    
    
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
        
        
        /*
        String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test_db";
		String user = "root";
		String password = "mysql_2018";
*/
    }
    
      
}
