package service.interaction.security;

public class TokenUser {
    private final String id;
    private final String userName;
    private final String departmentId;
    private final String permission;

    public TokenUser(String id, String userName, String departmentId, String permission) {
        this.id = id;
        this.userName = userName;
        this.departmentId = departmentId;
        this.permission = permission;
    }

    public String getId() {
        return id;
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

