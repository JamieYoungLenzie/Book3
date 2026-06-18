package com.sas.memex.book3.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppUsers {

    @XmlElement(name="user")
    private List<AppUser> users = new ArrayList<AppUser>();
    
    public AppUsers() {
        
    }
    
    /**
     * @return the users
     */
    public List<AppUser> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<AppUser> users) {
        this.users = users;
    }    
}
