/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alomsoft.capp.dao;

import com.alomsoft.capp.domain.Contact;
import com.alomsoft.capp.domain.User;
import java.util.List;

/**
 *
 * @author Mohammed
 */
public interface ContactDAO {
    
    public void save(Contact c);
    
    public void update( Contact c);
    
    public void delete( Contact c);
    
    public void delete(Integer contactId);
    
    public Contact findById(Integer contactId);
    
    public List<Contact> findAll();
    
    public List<Contact> findByProperty(String propName, Object propValue);
    
    
}
