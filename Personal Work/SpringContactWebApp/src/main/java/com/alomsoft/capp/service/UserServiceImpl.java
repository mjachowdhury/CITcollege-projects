 
package com.alomsoft.capp.service;

import com.alomsoft.capp.dao.BaseDAO;
import com.alomsoft.capp.dao.UserDAO;
import com.alomsoft.capp.domain.User;
import com.alomsoft.capp.exception.UserBlockedException;
import com.alomsoft.capp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

//Role 1 represent admin and 2 represen user 
/**
 *
 * @author Mohammed
 */
@Service
public class UserServiceImpl extends BaseDAO implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User u) {
         userDAO.save(u);
    }
/**
 * this method will work login status for user 
 * @param loginName
 * @param password
 * @return
 * @throws UserBlockedException 
 */
    @Override
    public User login(String loginName, String password) throws UserBlockedException {
        String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus "
                + " FROM user WHERE loginName=:ln AND password=:pw";
        Map m = new HashMap();
        m.put("ln", loginName);
        m.put("pw", password);
        
        try {           
            User u = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            if(u.getLoginStatus().equals(UserService.LOGIN_STATUS_BLOCKED)){
                throw new UserBlockedException("Your account has been blocked. Contact to admin.");
            }else{
                return u;//if data availible will retun true
            }
             
        } catch ( EmptyResultDataAccessException ex) {//if there no data then will get null value exception
            return null;
        }
        
    }

    @Override
    public List<User> getUserList() {
        return userDAO.findByProperty("role", UserService.ROLE_USER);
        
    }
/**
 * This method will change the status of the user in the server and database
 * @param userId
 * @param loginStatus 
 */
    @Override
    public void changeLoginStatus(Integer userId, Integer loginStatus) {
         String sql = "UPDATE user SET loginStatus=:lst WHERE userID=:uid";
         Map m = new HashMap();
         m.put("uid", userId);
         m.put("lst", loginStatus);
         getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public Boolean isUsernameExist(String username) {
        String sql = "SELECT count(loginName) FROM user WHERE loginName=?";
        Integer count = getJdbcTemplate().queryForObject(sql,new String[] {username}, Integer.class);
        if(count==1){
            return true;
        }else{
            return false;
        }  
    }
    
}
