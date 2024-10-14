package com.example.demo.domain.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    IZDAVAC_READ("izdavac:read"),
    IZDAVAC_UPDATE("izdavac:update"),
    IZDAVAC_CREATE("izdavac:create"),
    IZDAVAC_DELETE("izdavac:delete"),
    KORISNIK_READ("korisnik:read"),
    KORISNIK_CREATE("korisnik:create"),
    KORISNIK_DELETE("korisnik:delete")
    ;

    public String getPermission() {
		return permission;
	}

	private Permission(String permission) {
		this.permission = permission;
	}
	
    private final String permission;
}