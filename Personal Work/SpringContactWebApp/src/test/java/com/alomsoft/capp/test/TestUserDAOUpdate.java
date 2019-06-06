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
public class TestUserDAOUpdate {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);

        //the user details will be taken from Update user profile page
        User u = new User();
        u.setUserId(2);
        u.setName("Jahangir chowdhury");
        u.setPhone("0568923448");
        u.setEmail("jahangir@gmail.ie");
        u.setAddress("Cork");
        u.setLoginName("jahangir");
        u.setPassword("12345");
        u.setRole(1);//Admin Role
        u.setLoginStatus(1);//Active

        userDAO.update(u);
        System.out.println("==========Data Updated===========");

    }

}
