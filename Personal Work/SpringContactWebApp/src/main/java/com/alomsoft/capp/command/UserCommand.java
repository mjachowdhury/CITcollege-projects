 
package com.alomsoft.capp.command;

import com.alomsoft.capp.domain.User;

/**
 *
 * @author Mohammed
 */
public class UserCommand {
    User user;//reusing the domain class which is User
    //TODO: more fields if required

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
