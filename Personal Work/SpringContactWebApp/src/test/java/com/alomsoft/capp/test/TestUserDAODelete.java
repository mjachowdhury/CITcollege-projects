 
package com.alomsoft.capp.test;

import com.alomsoft.capp.config.SpringRootConfig;
import com.alomsoft.capp.dao.UserDAO;
import com.alomsoft.capp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Mohammed
 */
public class TestUserDAODelete {
    
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        userDAO.delete(2);
        
        
        System.out.println("==========Data Deleted===========");
        
                
       
    }
    
}
