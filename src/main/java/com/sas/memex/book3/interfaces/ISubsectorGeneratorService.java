package com.sas.memex.book3.interfaces;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.service.SubsectorGeneratorService;

public interface ISubsectorGeneratorService {
    Subsector generate(SubsectorGeneratorService.Density density);
    void print();    
}
