package com.wizardjava.validators;

import com.wizardjava.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator  implements Validator {

    private int minLengthPsw = 6;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;

        if(user.getUsername().isEmpty()) {
            errors.rejectValue("username", "users.username.empty");
        }

        if(user.getPassword().isEmpty() || user.getPassword().length() < minLengthPsw) {
            errors.rejectValue("password", "users.password.empty");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "users.password.confirm");
        }
    }
}
