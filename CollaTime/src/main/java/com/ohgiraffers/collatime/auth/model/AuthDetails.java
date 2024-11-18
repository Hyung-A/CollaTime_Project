
package com.ohgiraffers.collatime.auth.model;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthDetails implements UserDetails {

    private UserDTO userDTO;

    public AuthDetails(){}

    public AuthDetails(UserDTO userDTO){this.userDTO = userDTO;}

    public void setUserDTO(UserDTO userDTO){this.userDTO = userDTO;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        사용자의 권한 정보를 반환하기 위한 메소드
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        userDTO.getRole().forEach(role -> authorities.add(() -> role));

        return authorities;
    }

    @Override
    public String getPassword() {
        return userDTO.getUserPwd();
    }

    @Override
    public String getUsername() {
        return userDTO.getUserId();
    }

    public int getUserNo(){return userDTO.getUserNo();}
    public String getUserNickname(){return userDTO.getUserNickname();}
    public String getUserName(){ return userDTO.getUserName();}
    public String getUserColor(){return userDTO.getUserColor();}
    public String getUserEmail(){return userDTO.getUserEmail();}
    public String getUserPicture(){return userDTO.getUserPicture();}


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
