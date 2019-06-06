 
package com.alomsoft.capp.service;

import com.alomsoft.capp.domain.User;
import com.alomsoft.capp.exception.UserBlockedException;
import java.util.List;

/**
 *
 * @author Mohammed
 */
public interface UserService {
    
    public static final Integer LOGIN_STATUS_ACTIVE=1;
    public static final Integer LOGIN_STATUS_BLOCKED=2;
    
    public static final Integer ROLE_ADMIN=1;
    public static final Integer ROLE_USER=2;
    
    
    
    /**
     * The method handle user registration task.
     * @param u the new user detail as User object
     */
    public void register(User u);
    /**
     * The method handles login operation(authentication) using given credentials, 
     * it returns User object when success and null when failed. when user account is blocked 
     * and an exception will be thrown by this method
     * @param loginName
     * @param password
     * @return 
     * @throws  com.alomsoft.capp.exception.UserBlockedException when user account is blocked
     */
    public User login(String loginName, String password) throws UserBlockedException;
    
    /**
     * Call this method to get list of registered user
     * @return 
     */
    public List<User> getUserList();
    
    /**
     * THis method change the user login status for details passed s argument 
     * @param userId
     * @param loginStatus 
     */
    public void changeLoginStatus(Integer userId, Integer loginStatus);
    
    /**
     * Check the username availability
     * @param username
     * @return 
     */
    public Boolean isUsernameExist(String username);
        
}
