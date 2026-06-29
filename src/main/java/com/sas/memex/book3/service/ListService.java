package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.IListService;
import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.model.SubsectorList;
import com.sas.memex.book3.model.SubsectorListItem;
import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class ListService implements IListService {

    private final String folder;

    public ListService(@Qualifier("dataFolder") String dataFolder) {
        folder = dataFolder;
    }

    @Override
    public SubsectorList getList() {

        SubsectorList subsectorList = new SubsectorList();

        try {
            if (folder == null || folder.trim().isEmpty()) {
                throw new CustomException("Data folder is not configured");
            }

            File directory = new File(folder);

            if (!directory.exists() || !directory.isDirectory()) {
                throw new CustomException("Data folder does not exist or is not a directory: " + folder);
            }

            File[] files = directory.listFiles();

            if (files == null) {
                return subsectorList;
            }

            for (File file : files) {
                if (file == null) {
                    continue;
                }

                if (getFileExtensionLowerCase(file).equals("xml") && file.isFile()) {
                    subsectorList.getItems().add(new SubsectorListItem(getFileNameWithoutExtension(file)));
                }
            }
        } catch (Exception ex) {
            throw new CustomException(ex);
        }

        return subsectorList;
    }

    private String getFileNameWithoutExtension(File file) {
        int lastIndexOfDot = file.getName().lastIndexOf('.');
        return file.getName().substring(0, lastIndexOfDot);
    }

    private String getFileExtensionLowerCase(File file) {

        String fileName = file.getName();

        int extensionIndex = fileName.lastIndexOf('.');

        if (extensionIndex >= 0) {
            return fileName.substring(extensionIndex + 1).toLowerCase();
        } else {
            return "";
        }
    }
}
