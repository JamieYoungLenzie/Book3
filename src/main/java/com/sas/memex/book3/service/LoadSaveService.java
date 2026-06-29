package com.sas.memex.book3.service;

import com.sas.memex.book3.helper.Marshal;
import com.sas.memex.book3.interfaces.ILoadSaveService;
import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.helper.Sanitise;
import com.sas.memex.book3.model.Subsector;
import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class LoadSaveService implements ILoadSaveService {

    private final String folder;

    public LoadSaveService(@Qualifier("dataFolder") String dataFolder) {
        folder = dataFolder;
    }
    
    @Override
    public void saveSubsector(Subsector subsector, String subsectorName) {
        try {
            if (Sanitise.isFilename(subsectorName)) {
                String path = folder + File.separator + subsectorName + ".xml";
                Marshal.marshal(subsector, path);
            } else {
                throw new IllegalArgumentException(String.format("Invalid argument: %s", subsectorName));
            }
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }

    @Override
    public Subsector loadSubsector(String subsectorName) {
        try {
            if (Sanitise.isFilename(subsectorName)) {
                String path = String.format("%s%s%s.xml", folder, File.separator, subsectorName);
                return Marshal.unmarshal(Subsector.class, path);
            } else {
                throw new IllegalArgumentException(String.format("Invalid argument: %s", subsectorName));
            }
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }

    @Override
    public Boolean subsectorExists(String subsectorName) {
        try {
            String path = String.format("%s%s%s.xml", folder, File.separator, subsectorName);
            File file = new File(path);
            return file.exists();
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }
}
