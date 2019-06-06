 
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
    
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        //the user details will be taken from through reg-form
        User u = new User();
        u.setName("Jahangir");
        u.setPhone("0568923448");
        u.setEmail("jahangir@yahoo.ie");
        u.setAddress("Ireland");
        u.setLoginName("jahangir");
        u.setPassword("12345");
        u.setRole(1);//Admin Role
        u.setLoginStatus(1);//Active
        
        userDAO.save(u);
        System.out.println("==========Data Saved===========");
        
                
       
    }
    
}
