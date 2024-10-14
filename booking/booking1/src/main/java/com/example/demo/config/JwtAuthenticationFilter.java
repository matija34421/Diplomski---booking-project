package com.example.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.domain.entities.User;
import com.example.demo.repository.repositores.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String email;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		
		email = jwtService.extractEmail(jwt);
		
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = repository.findByEmail(email);
			if (user != null) {
			    System.out.println("User roles: " + user.getAuthorities());
			}

			if(jwtService.isTokenValid(user, jwt)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						user,
						null,
						user.getAuthorities());
				authToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request)
				);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}

