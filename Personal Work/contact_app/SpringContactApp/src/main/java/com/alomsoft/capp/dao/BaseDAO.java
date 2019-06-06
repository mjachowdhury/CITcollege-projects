
package com.alomsoft.capp.dao;

import javax.activation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

/**
 *
 * @author Mohammed
 */
//NOTE: do not @Repository or @Service or @ Component annotation
abstract public class BaseDAO extends NamedParameterJdbcDaoSupport{
    @Autowired
    public void setDataSource2(DataSource ds){
        super.setDataSource((javax.sql.DataSource)ds);
        
    }
    
}
