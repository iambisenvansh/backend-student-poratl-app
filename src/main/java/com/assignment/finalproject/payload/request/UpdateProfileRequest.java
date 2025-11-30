package com.assignment.finalproject.payload.request;

import jakarta.validation.constraints.*;

public class UpdateProfileRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @NotBlank
    private String department;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
