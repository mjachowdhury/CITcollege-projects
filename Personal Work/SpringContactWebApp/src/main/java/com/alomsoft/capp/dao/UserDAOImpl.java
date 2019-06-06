 
package com.alomsoft.capp.dao;

import com.alomsoft.capp.domain.User;
import com.alomsoft.capp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohammed
 */

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO{

    @Override
    public void save(User u) {
        String sql = "INSERT INTO user(name,phone,email,address,loginName,password, role, loginStatus)"
                + " VALUES(:name, :phone, :email, :address, :loginName, :password, :role, :loginStatus)";//these are the named the parameters this is how to do 
        Map m = new HashMap();//binding the data through the map 
        
        m.put("name", u.getName());
        m.put("phone", u.getPhone());
        m.put("email", u.getEmail());
        m.put("address", u.getAddress());
        m.put("loginName", u.getLoginName());
        m.put("password", u.getPassword());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginStatus());
        
        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        getNamedParameterJdbcTemplate().update(sql,ps, kh);
        Integer userId = kh.getKey().intValue();
        u.setUserId(userId);
    }

    @Override
    public void update(User u) {
        String sql = "UPDATE user "
                                    + " SET name=:name,"//these are named the parameter
                                    + " phone=:phone,"
                                    + " email=:email,"
                                    + " address=:address,"
                                    + " role=:role,"
                                    + " loginStatus=:loginStatus "  
                                    + " WHERE userId=:userId"; 
        Map m= new HashMap(); //named the parameter are bind by Map
        m.put("name", u.getName());
        m.put("phone", u.getPhone());
        m.put("email", u.getEmail());
        m.put("address", u.getAddress());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginStatus());
        m.put("userId", u.getUserId());
        
        getNamedParameterJdbcTemplate().update(sql, m);
        
    }

    @Override
    public void delete(User u) {
        this.delete(u.getUserId());
    }

    @Override
    public void delete(Integer userId) {
        String sql="DELETE FROM user WHERE userId=?";
        getJdbcTemplate().update(sql, userId);
    }

    @Override
    public User findById(Integer userId) {
        String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
                + " FROM user WHERE userId=?";
        User u = getJdbcTemplate().queryForObject(sql, new UserRowMapper(),userId);
        return u;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
                + " FROM user";
        /*
        List<User> users = getJdbcTemplate().query(sql, new UserRowMapper());
        return users;
        */
        return getJdbcTemplate().query(sql, new UserRowMapper());
    }

    @Override
    public List<User> findByProperty(String propName, Object propValue) {
        String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
                + " FROM user WHERE "+propName+"=?";
        return getJdbcTemplate().query(sql, new UserRowMapper(), propValue);
    }
    
    
}
