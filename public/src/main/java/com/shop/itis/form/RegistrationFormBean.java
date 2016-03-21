package com.shop.itis.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationFormBean {

    @NotEmpty(message = "Это поле обязательно для заполнения")
    String username;


    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Size(min = 6, max = 20, message = "Пароль должен быть от 6 до 20 символов")
    String password;


    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message = "Такой вид адреса почты не может быть")
    String email;

    public RegistrationFormBean() {
    }

    public RegistrationFormBean(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
