package com.sas.memex.book3.interfaces;

import com.sas.memex.book3.model.Subsector;

public interface ILoadSaveService {
    Subsector loadSubsector(String subsectorName);
    void saveSubsector(Subsector subsector, String subsectorName);  
    Boolean subsectorExists(String subsectorName);
}
