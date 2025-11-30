package com.assignment.finalproject.payload.response;

import com.assignment.finalproject.model.UserType;

public class JwtResponse {

    private String token;
    private Long id;
    private String name;
    private String email;
    private UserType type;

    public JwtResponse(String token, Long id, String name, String email, UserType type) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }
}
