package ru.romanov.modulefive.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.romanov.modulefive.domain.Role;
import ru.romanov.modulefive.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRolesSet = user.getRolesSet();
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        for (Role role : userRolesSet) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthoritiesList.add(authority);
        }
        return grantedAuthoritiesList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
