package com.assignment.finalproject.payload.request;

import jakarta.validation.constraints.NotBlank;

public class CourseRequest {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;

    // getters / setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) { this.code = code; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }
}
