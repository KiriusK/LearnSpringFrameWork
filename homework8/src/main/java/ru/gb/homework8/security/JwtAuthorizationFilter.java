package ru.gb.homework8.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.gb.homework8.services.JwtService;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;



    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtService = jwtService;
        this.userDetailsService  = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader(jwtService.getHEADER());
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith(jwtService.getTOKEN_PREFIX())) {
            chain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(jwtService.getTOKEN_PREFIX().length());
        String username = jwtService.getTokenUsername(jwt);
        UserDetails user = null;
        try {
            user = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException ex) {
            System.out.println("Пользователь не найден");
            response.addHeader("Server response", "User not found");
            chain.doFilter(request, response);
        }
        if (user != null || jwtService.isTokenValid(jwt)) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }
        chain.doFilter(request, response);
    }
}
