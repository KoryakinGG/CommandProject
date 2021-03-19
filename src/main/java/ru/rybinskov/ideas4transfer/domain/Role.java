package ru.rybinskov.ideas4transfer.domain;

public enum Role {

    ADMIN("ADMIN"),
    BRAND_MANAGER("BRAND_MANAGER"),
    WAREHOUSE("WAREHOUSE");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {return role;}
}
