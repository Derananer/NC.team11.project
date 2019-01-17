package com.company.employeeMicroservice.security;

public class TokenUser {
    private final String userName;
    private final String departmentId;
    private final String permission;

    public TokenUser(String userName, String departmentId, String permission) {
        this.userName = userName;
        this.departmentId = departmentId;
        this.permission = permission;
    }



    public String getUserName() {
        return userName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getPermission() {
        return permission;
    }
}
