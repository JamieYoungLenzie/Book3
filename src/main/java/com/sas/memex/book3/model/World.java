package com.sas.memex.book3.model;
import org.springframework.stereotype.Component;

@Component
public class World {

    private String name;
    private String location;
    private String upp;
    
    private String starport;
    private int size;
    private int atmosphere;
    private int hydrosphere;
    private int population;
    private int government;
    private int lawLevel;
    private int techLevel;
    private String comment;
    
    public World() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getUpp() {
        return upp;
    }
    
    public void setUpp(String upp) {
        this.upp = upp;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getStarport() {
        return starport;
    }
    
    public void setStarport(String starport) {
        this.starport = starport;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getAtmosphere() {
        return atmosphere;
    }
    
    public void setAtmosphere(int atmosphere) {
        this.atmosphere = atmosphere;
    }
    
    public int getHydrosphere() {
        return hydrosphere;
    }
    
    public void setHydrosphere(int hydrosphere) {
        this.hydrosphere = hydrosphere;
    }
    
    public int getPopulation() {
        return population;
    }
    
    public void setPopulation(int population) {
        this.population = population;
    }
    
    public int getGovernment() {
        return government;
    }
    
    public void setGovernment(int government) {
        this.government = government;
    }
    
    public int getLawLevel() {
        return lawLevel;
    }
    
    public void setLawLevel(int lawLevel) {
        this.lawLevel = lawLevel;
    }
    
    public int getTechLevel() {
        return techLevel;
    }
    
    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
        return this.comment;
    }
}
