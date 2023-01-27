package com.example.magazine.configuration;

import com.example.magazine.entity.Profile;
import com.example.magazine.type.Role;
import com.example.magazine.type.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private ProfileStatus status;
    private Role role;

    private List<GrantedAuthority> authorityList;

    public CustomUserDetails(Profile profile){
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.password = profile.getPassword();
        this.status = profile.getStatus();
        this.role = profile.getRole();

        this.authorityList = List.of(new SimpleGrantedAuthority(role.toString()));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //todo:change enabled
    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
