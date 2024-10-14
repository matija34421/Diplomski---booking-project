package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.domain.entities.Permission;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private String[] whitelist = {
			"/api/auth/register", 
			"/api/auth/login" , 
			"/api/pictures/{id}",
			"/api/pcitures",
			"/api/pictures/uploadPicture",
			"/api/properties/all",
			"/api/properties/{id}",
			"/api/properties/reviews/{id}",
			"/api/reservations",
			"/api/reservations/propertyId/{id}",
			"/api/reservations/userId/{id}",
			"/api/reservations/{id}",
			"/api/users",
			"/api/users/{id}"
	};
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(Arrays.asList("*"));
            config.setExposedHeaders(Arrays.asList("Authorization"));
            config.setAllowCredentials(true);
            return config;
        }))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(whitelist).permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/pictures/{id}").hasAuthority(Permission.IZDAVAC_UPDATE.getPermission())
            .requestMatchers(HttpMethod.POST, "/api/properties").hasAuthority(Permission.IZDAVAC_CREATE.getPermission())
            .requestMatchers(HttpMethod.DELETE, "/api/properties/{id}").hasAuthority(Permission.IZDAVAC_DELETE.getPermission()) 
            .requestMatchers(HttpMethod.PUT, "/api/properties").hasAuthority(Permission.IZDAVAC_UPDATE.getPermission())
            .requestMatchers(HttpMethod.POST, "/api/properties/reviews").hasAuthority(Permission.KORISNIK_CREATE.getPermission())
            .requestMatchers(HttpMethod.POST, "/api/reservations").hasAnyAuthority(Permission.KORISNIK_CREATE.getPermission(), Permission.IZDAVAC_CREATE.getPermission())
            .requestMatchers(HttpMethod.DELETE, "/api/reservations/{id}").hasAnyAuthority(Permission.KORISNIK_DELETE.getPermission(), Permission.IZDAVAC_DELETE.getPermission())
            .requestMatchers(HttpMethod.PUT, "/api/reservations").hasAuthority(Permission.IZDAVAC_UPDATE.getPermission())
            .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAuthority(Permission.ADMIN_DELETE.getPermission())
            .requestMatchers(HttpMethod.PUT, "/api/users").hasAuthority(Permission.ADMIN_UPDATE.getPermission())
            .anyRequest().permitAll()
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
			
			
		return http.build();
	}
}
