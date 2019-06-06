 
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
public class TestUserDAOSave {
    public static void main (String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        //User details will be taken from reg-form
        User u = new User();
        u.setName("Mohammed");
        u.setPhone("123456");
        u.setEmail("Hello@yahoo.com");
        u.setAddress("Ireland");
        u.setLoginName("alom");
        u.setPassword("alom113");
        u.setRole(1);//Admin Role
        u.setLoginStatus(1);//Active status
        userDAO.save(u);
        System.out.println("----------Data Saved-----------");
        
        
    }
    
}
