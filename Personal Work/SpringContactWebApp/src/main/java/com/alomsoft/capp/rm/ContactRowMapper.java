 
package com.alomsoft.capp.rm;

import com.alomsoft.capp.domain.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Mohammed
 */
public class ContactRowMapper implements RowMapper<Contact>{

    @Override
    public Contact mapRow(ResultSet rs, int i) throws SQLException {
        Contact c = new Contact();
        c.setContactId(rs.getInt("contactId"));
        c.setUserId(rs.getInt("userId"));
        c.setName(rs.getString("name"));
        c.setPhone(rs.getString("phone"));
        c.setEmail(rs.getString("email"));      
        c.setAddress(rs.getString("address"));
        c.setRemark(rs.getString("remark"));
         
        return c;
        
    }
    
}
