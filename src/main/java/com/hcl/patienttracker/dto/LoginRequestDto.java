package com.hcl.patienttracker.dto;

public class LoginRequestDto {
//    @NotBlank(message = "email is manadtory")
    private  String email;
//    @NotBlank(message = "Password is mandatory")
    private  String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
