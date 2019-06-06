 
package com.alomsoft.capp.test;

import com.alomsoft.capp.config.SpringRootConfig;
import com.alomsoft.capp.domain.User;
import com.alomsoft.capp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Mohammed
 */
public class TestUserServiceRegister {
    
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
         
        UserService userService = ctx.getBean(UserService.class);
        //the user details will be taken from through reg-form
        User u = new User();
        u.setName("Robeul");
        u.setPhone("154788");
        u.setEmail("robeul@yahoo.ie");
        u.setAddress("Bangladesh");
        u.setLoginName("robeul");
        u.setPassword("robeul");
        u.setRole(UserService.ROLE_ADMIN);//Admin Role
        u.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);//Active
        
        userService.register(u);
        System.out.println("==========User Registered Successfully===========");
        
                
       
    }
    
}
