package com.sas.memex.book3.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Subsector")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subsector {
    
    @XmlElement(name="World")
    private List<World> worlds = new ArrayList<World>();
    private String name;
    
    public Subsector() {
        
    }
    
    public List<World> getWorlds() {
        return this.worlds;
    }
    
    public void setWorlds(List<World> worlds) {
        this.worlds = worlds;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
