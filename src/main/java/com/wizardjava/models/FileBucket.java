package com.wizardjava.models;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Codeine on 12.12.2016.
 */
public class FileBucket {

    private String nameFile;
    private String descriptionFile;
    private String alt;
    MultipartFile file;

    public FileBucket() {
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getDescriptionFile() {
        return descriptionFile;
    }

    public void setDescriptionFile(String descriptionFile) {
        this.descriptionFile = descriptionFile;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
