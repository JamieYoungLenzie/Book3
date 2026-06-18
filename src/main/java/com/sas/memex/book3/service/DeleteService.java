package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.IDeleteService;
import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.helper.Sanitise;
import java.io.File;
import org.springframework.stereotype.Service;

@Service
public class DeleteService implements IDeleteService {
    
    private final String folder;

    // Autowired unnecessary with single constructor.
    public DeleteService(String dataFolder) {
        folder = dataFolder;
    }
    
    @Override
    public void deleteSubsector(String subsectorName) {        
        try {
            if (Sanitise.isFilename(subsectorName)) {
                String path = folder + File.separator + subsectorName + ".xml";
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
            } else {
                throw new IllegalArgumentException(String.format("Invalid argument: %s", subsectorName));
            }                
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }
}
