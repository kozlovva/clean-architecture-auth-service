package ru.foodtechlab.callcenter.auth.config.security;

import com.rcore.domain.security.model.CredentialDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class UserPrincipal implements UserDetails {

    private String id;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean isAnonymous;

    public static UserPrincipal from(CredentialDetails credentialDetails) {
        return new UserPrincipal(
                credentialDetails.getId(),
                credentialDetails.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()),
                false
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
