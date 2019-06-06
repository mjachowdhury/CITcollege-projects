 
package com.alomsoft.capp.test;

import com.alomsoft.capp.config.SpringRootConfig;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Mohammed
 */


public class TestDataSource {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        JdbcTemplate jt = new JdbcTemplate(ds);
        String sql = "INSERT INTO user (`name`, `phone`, `email`, `address`, `loginName`, `password`)VALUES(?,?,?,?,?,?)";
        Object[] param = new Object[]{"Mohammed","0862354995","alom@yahoo.com","Cork","alom","alom123"};
        jt.update(sql,param);
        System.out.println("=========SQL Executed=============");
        
    }
    
}
