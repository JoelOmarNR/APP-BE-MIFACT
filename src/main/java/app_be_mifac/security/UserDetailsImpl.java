package app_be_mifac.security;

import app_be_mifac.entity.seguridad.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final Integer id;
    private final String name;
    private final String username;
    private final String password;
    private final String role;
    private final List<GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getNombreCompleto();
        this.username = usuario.getEmail();
        this.password = usuario.getPassword();
        this.role = usuario.getRol().name();
        this.grantedAuthorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
