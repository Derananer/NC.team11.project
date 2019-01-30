package com.company.authorisationservice;

public enum Permission {

    USER("user"),
    ADMIN("admin"),
    VIEWER("viewer");

    private final String value;

    Permission(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
