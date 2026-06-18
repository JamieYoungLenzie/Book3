package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.IEditService;
import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.model.Subsector;
import org.springframework.stereotype.Service;

@Service
public class EditService implements IEditService {
    
    private final DeleteService deleteService;
    private final LoadSaveService loadSaveService;
    
    // Autowired unnecessary with single constructor.
    public EditService(DeleteService deleteService, LoadSaveService loadSaveService) {
        this.deleteService = deleteService;
        this.loadSaveService = loadSaveService;
    }
    
    @Override
    public Subsector edit(Subsector subsector, String subsectorName) {        
        try {            
            deleteService.deleteSubsector(subsectorName);
            loadSaveService.saveSubsector(subsector, subsectorName);
            return subsector;            
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }
}
