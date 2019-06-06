 
package com.alomsoft.capp.controller;

import com.alomsoft.capp.command.LoginCommand;
import com.alomsoft.capp.command.UserCommand;
import com.alomsoft.capp.domain.User;
import com.alomsoft.capp.exception.UserBlockedException;
import com.alomsoft.capp.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Mohammed
 */
@Controller 

public class UserController {
    @Autowired
    private UserService userService;
  
    @RequestMapping(value = {"/","/index"})//this is how to do multiple url and default page
    public String index(Model m){
        m.addAttribute("command", new LoginCommand());
        return "index"; //JSP-/WEB-INF/view/index.jsp
    }
    
   @RequestMapping(value = "/login", method = RequestMethod.POST)// this mapping for login
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m, HttpSession session){
        try {
            User loggedInUser = userService.login(cmd.getLoginName(), cmd.getPassword());
            if(loggedInUser == null){
                //failed part
                 m.addAttribute("err", "Login failed! Enter valid crediantial.");
                 return "index";//JSP -Login FORM
            }else{
                //success part
                //check the role and redirect to a appropriate dashboard
                if(loggedInUser.getRole().equals(UserService.ROLE_ADMIN)){
                    //add user details in session (assign session to logged in user)
                    addUserInSession(loggedInUser, session); //this will provide the user logged in session time
                    return "redirect:admin/dashboard";
                    
                }else if(loggedInUser.getRole().equals(UserService.ROLE_USER)){
                     //add user details in session (assign session to logged in user)
                      addUserInSession(loggedInUser, session);//this will provide the user logged in session time
                     return "redirect:user/dashboard";
                }else{
                     //add error message and go back to login-form
                      m.addAttribute("err", "Invalid User ROLE");
                        return "index";//JSP -Login FORM
                }
            }
        } catch (UserBlockedException ex) {
            //add error message and go back to login-form
           m.addAttribute("err", ex.getMessage());
           return "index";//JSP -Login FORM
        }
        
    }
    
    
     @RequestMapping(value = "/logout") 
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index?act=lo"; //lo represent logout
    }
    
     @RequestMapping(value = "/user/dashboard") 
    public String userDashboard(){
        return "dashboard_user"; //JSP 
    }
    
    /**
     * This method will return to the Admin dashboard
     * @return 
     */
     @RequestMapping(value = "/admin/dashboard") 
    public String adminDashboard(){
        return "dashboard_admin"; //JSP 
    }
    
    /**
     * This method will return all the user list from the database
     * @param m
     * @return 
     */
    @RequestMapping(value = "/admin/users") 
    public String getUserList(Model m){
        m.addAttribute("userList", userService.getUserList());
        return "users"; //JSP 
    }
    /**
     * this is for register user method 
     * @param m
     * @return 
     */
    @RequestMapping(value = "/reg_form")//this are mapping
    public String registrationForm(Model m){
        UserCommand cmd = new UserCommand();
        m.addAttribute("command", cmd);//this is binding
        return "reg_form";//jsp page
    }
    
    /**
     * this methd for submit funcgtion
     * @param cmd
     * @param m
     * @return 
     */
    @RequestMapping(value = "/register")//this are mapping
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model m){
        try {
            User user = cmd.getUser();
            user.setRole(UserService.ROLE_USER);
            user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
            userService.register(user);
            //m.addAttribute("command", cmd);
            return "redirect:index?act=reg";//LOgin page
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            m.addAttribute("err", "Username is already registered. Please select another usernmae.");
            return "reg_form";//JSP page
        }
    }
    /**
     * This method will provide the user logged in session information
     * @param u
     * @param session 
     */
    private void addUserInSession(User u, HttpSession session){
        session.setAttribute("user", u);
        session.setAttribute("userId", u.getUserId());
        session.setAttribute("role", u.getRole());
        
    }
    
    
    @RequestMapping(value = "/admin/change_status")//this are mapping
    @ResponseBody
    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus){
         try {
            userService.changeLoginStatus(userId, loginStatus);
            return "SUCCESS: Status Changed";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: Unable to Change Status";
        }
    }
    
    
    @RequestMapping(value = "/check_avail")//this are mapping
    @ResponseBody
    public String checkAvailability(@RequestParam String username){
          if(userService.isUsernameExist(username)){
              return "The username is already taken. Choose another name";
          }else{
              return "Yes! You can take this username";
          }
    }
}
