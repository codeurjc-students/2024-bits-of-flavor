package es.codeurjc.bof.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.codeurjc.bof.security.jwt.JwtRequestFilter;
import es.codeurjc.bof.security.jwt.UnauthorizedHandlerJwt;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public RepositoryUserDetailsService userDetailService;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apifilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
        .securityMatcher("/api/**")
        .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));

        http
                .authorizeHttpRequests(authorize -> authorize

                        // PRIVATE ENDPOINTS
                        .requestMatchers(HttpMethod.POST, "api/product/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "api/product/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/product/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "api/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "api/ticket/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "api/ticket/**").hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "api/offers/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/offers/**").hasAnyRole("ADMIN")
                        // PUBLIC ENDPOINTS
                        .anyRequest().permitAll());

        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT Token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webfilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .authorizeHttpRequests(authorize -> authorize
                // PRIVATE ENDPOINTS
                        .requestMatchers("/ticket/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/profile").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/newProduct").hasAnyRole("ADMIN")
                        .requestMatchers("/offerManagement").hasAnyRole("ADMIN")
                        // Public PAGES
                        .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/error")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }
    
}
