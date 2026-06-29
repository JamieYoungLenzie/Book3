package com.sas.memex.book3.helper;

import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.model.Subsector;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;

public class Marshal {

    public static <T> void marshal(T object, String filename) {
        if (object == null) {
            throw new CustomException(new IllegalArgumentException("Object cannot be null"));
        }
        if (filename == null || filename.trim().isEmpty()) {
            throw new CustomException(new IllegalArgumentException("Filename cannot be null or empty"));
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            try (FileWriter writer = new FileWriter(filename)) {
                marshaller.marshal(object, writer);
            }
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }

    public static Subsector unmarshal(String filename) {
        return unmarshal(Subsector.class, filename);
    }

    public static <T> T unmarshal(Class<T> clazz, String filename) {
        if (clazz == null) {
            throw new CustomException(new IllegalArgumentException("Class cannot be null"));
        }
        if (filename == null || filename.trim().isEmpty()) {
            throw new CustomException(new IllegalArgumentException("Filename cannot be null or empty"));
        }

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T object = clazz.cast(unmarshaller.unmarshal(new File(filename)));
            return object;
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }
}
