package com.ohgiraffers.collatime.auth.model;

import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthDetails implements UserDetails {

    private UserDTO userDTO;

    public AuthDetails(){}

    public AuthDetails(UserDTO userDTO){this.userDTO = userDTO;}

    public UserDTO getUserDTO(){return userDTO;}

    public void setUserDTO(UserDTO userDTO){this.userDTO = userDTO;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
