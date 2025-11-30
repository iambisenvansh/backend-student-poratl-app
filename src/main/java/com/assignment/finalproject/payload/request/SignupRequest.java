package com.assignment.finalproject.payload.request;

import com.assignment.finalproject.model.UserType;
import jakarta.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String gender;

    @NotBlank
    private String department;

    // Optional: default STUDENT
    private UserType type = UserType.STUDENT;

    // getters / setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
