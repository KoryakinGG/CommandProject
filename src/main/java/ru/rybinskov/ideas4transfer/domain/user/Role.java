package ru.rybinskov.ideas4transfer.domain.user;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_TC_MANAGER("ROLE_TC_MANAGER");

    private final String role;

    private Role(final String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
