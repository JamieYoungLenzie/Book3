package com.sas.memex.book3.helper;

public class Sanitise {

    public static Boolean isFilename(String filename) {
        String regex = "[^\\s\\p{Punct}{}\\/]*";
        return filename.matches(regex);
    }

}
