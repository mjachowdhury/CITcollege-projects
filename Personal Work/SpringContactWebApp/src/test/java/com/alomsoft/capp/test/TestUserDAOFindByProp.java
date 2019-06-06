 
package com.alomsoft.capp.test;

import com.alomsoft.capp.config.SpringRootConfig;
import com.alomsoft.capp.dao.UserDAO;
import com.alomsoft.capp.domain.User;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Mohammed
 */
public class TestUserDAOFindByProp {
    
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        
        //List<User> users = userDAO.findByProperty("userId", 1);//this line will find by userId and value
        //List<User> users = userDAO.findByProperty("name", "Mohammed");//this line will find by name and value
        List<User> users = userDAO.findByProperty("role", 2);
        for(User u : users){
        System.out.println(u.getUserId()+" "+u.getName()+" "+u.getRole());
        //TODO: access other columns
        }
       
    }
    
}
