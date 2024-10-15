package com.example.demo.domain.entities;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Role {
    KORISNIK(Set.of(
    			Permission.KORISNIK_CREATE,
    			Permission.KORISNIK_DELETE,
    			Permission.KORISNIK_READ
    		)),
    IZDAVAC(
        Set.of(
            Permission.IZDAVAC_CREATE,
            Permission.IZDAVAC_DELETE,
            Permission.IZDAVAC_READ,
            Permission.IZDAVAC_UPDATE,
            Permission.KORISNIK_READ
        )),
    ADMIN(
        Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE,
            Permission.IZDAVAC_CREATE,
            Permission.IZDAVAC_DELETE,
            Permission.IZDAVAC_READ,
            Permission.IZDAVAC_UPDATE,
            Permission.KORISNIK_READ
        ));

	private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
