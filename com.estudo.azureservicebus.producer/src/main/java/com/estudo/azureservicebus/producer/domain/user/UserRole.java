package com.estudo.azureservicebus.producer.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    TEST("test");

    private String role;

    UserRole(String role){this.role = role;}

    public String getRole(String role){
        return role;
    }
}
