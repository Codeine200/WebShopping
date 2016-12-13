package com.wizardjava.validators;

import com.wizardjava.models.FileBucket;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Codeine on 12.12.2016.
 */
public class ImageValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FileBucket file = (FileBucket)o;

        if(file.getFile() != null && file.getFile().getOriginalFilename().length() != 0){
            if(file.getFile().getSize() == 0) {
                errors.rejectValue("file", "common.file.empty");
            }

            if(!file.getFile().getContentType().equals("image/jpeg") || !file.getFile().getContentType().equals("image/png")) {
                errors.rejectValue("file", "common.file.incorrect_type");
            }
        }
    }
}
