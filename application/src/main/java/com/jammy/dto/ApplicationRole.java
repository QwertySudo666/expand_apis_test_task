package com.jammy.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class ApplicationRole implements GrantedAuthority {
    private UUID roleId;

    private String authority;

    public ApplicationRole() {
        super();
    }

    public ApplicationRole(String authority) {
        this.authority = authority;
    }

    public ApplicationRole(UUID roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UUID getRoleId() {
        return this.roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }
}
