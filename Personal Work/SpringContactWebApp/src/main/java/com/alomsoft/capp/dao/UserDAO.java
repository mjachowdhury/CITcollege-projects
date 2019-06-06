/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alomsoft.capp.dao;

import com.alomsoft.capp.domain.User;
import java.util.List;

/**
 *
 * @author Mohammed
 */
public interface UserDAO {
    public void save(User u);
    
    public void update(User u);
    
    public void delete(User u);
    
    public void delete(Integer userId);
    
    public User findById(Integer userId);
    
    public List<User> findAll();
    
    public List<User> findByProperty(String propName, Object propValue);
    
    
     
}
