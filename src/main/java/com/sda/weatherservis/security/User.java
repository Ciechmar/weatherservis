package com.sda.weatherservis.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String authorities; // "ROLE_USER;email_sender"

    public List<GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(";"))
                .map(s -> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(";"));
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