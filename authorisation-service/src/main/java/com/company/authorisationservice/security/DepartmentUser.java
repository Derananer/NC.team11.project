package com.company.authorisationservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class DepartmentUser extends User {

    final String departmentId;

    public DepartmentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String departmentId) {
        super(username, password, authorities);
        this.departmentId = departmentId;
    }

    public DepartmentUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String departmentId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
