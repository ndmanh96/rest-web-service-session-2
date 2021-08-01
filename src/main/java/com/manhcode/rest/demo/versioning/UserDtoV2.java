package com.manhcode.rest.demo.versioning;

public class UserDtoV2 {
    private Long id;
    private String username;
    private String email;
    private String role;

    public UserDtoV2() {}

    public UserDtoV2(Long id, String username, String email, String rolename) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
