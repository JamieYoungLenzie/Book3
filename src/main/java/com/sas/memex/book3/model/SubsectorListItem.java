package com.sas.memex.book3.model;
import org.springframework.stereotype.Component;

@Component
public class SubsectorListItem {

    private String _name;

    public SubsectorListItem() {

    }

    public SubsectorListItem(String name) {
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
}
