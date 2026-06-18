package com.sas.memex.book3.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SubsectorList {
    
    private final List<SubsectorListItem> _items = new ArrayList<SubsectorListItem>();
    
    public SubsectorList() {
        
    }
    
    public List<SubsectorListItem> getItems() {
        return _items;
    }
}
