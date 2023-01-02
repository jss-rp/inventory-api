package com.jss.inventory.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.Set;

@Data
@Table(name = "user__")
public class User {

    private Long id;

    private String username;
    private String password;
    private String role;

    public AuthenticatedUser toAuthenticatedUser() {
        final Set<GrantedAuthority> grantedAuthorities = Collections.singleton((GrantedAuthority) () -> role);
        return new AuthenticatedUser(username, password, grantedAuthorities);
    }
}
