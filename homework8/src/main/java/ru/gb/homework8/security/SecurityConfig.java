package ru.gb.homework8.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.gb.homework8.aspects.RequestInterceptor;
import ru.gb.homework8.services.JwtService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    /**
     * Create 2 users in memory
     *
     *
     */
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(users.username("user").password("usrpwd").roles("USER").build());
        manager.createUser(users.username("admin").password("admpwd").roles("ADMIN").build());
        return manager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(new SimplePasswordEncoder());
        return new ProviderManager(authProvider);
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtService jwtService = new JwtService();
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager(), jwtService,userDetailsService());
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), jwtService);
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/notes/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/notes/*", "/notes").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/notes/status/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/notes/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/notes/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/notes").hasRole("ADMIN")
                        .anyRequest().denyAll())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
