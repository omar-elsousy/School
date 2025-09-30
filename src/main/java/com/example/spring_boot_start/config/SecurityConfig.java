package com.example.spring_boot_start.config;

import com.example.spring_boot_start.config.filters.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private AuthConfig authConfig;
    @Autowired
    public SecurityConfig(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            http.sessionManagement(session->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            http.csrf(httpSecurityCsrfConfigurer ->
                    httpSecurityCsrfConfigurer.disable());

            http.authorizeHttpRequests(
                    api->api
//                            .requestMatchers("/eraa-soft/**").hasRole("ADMIN")
//                            .requestMatchers(HttpMethod.GET,"/student").hasAnyRole("USER")
                            .requestMatchers("/auth/**").permitAll().anyRequest().authenticated()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
//            http.httpBasic(Customizer.withDefaults());
            http.addFilterBefore(authConfig, UsernamePasswordAuthenticationFilter.class);
            http.httpBasic(AbstractHttpConfigurer::disable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails1=
//                User.withUsername("omar")
//                        .password("{bcrypt}$2a$12$5WwSGyZku3PqdTfr7k70RuJXvnu/gWD2vmY165QhNDv.0dCwmG/ru")
//                        .roles("ADMIN","MANAGER","USER")
//                        .build();
//        UserDetails userDetails2=
//                User.withUsername("mariam")
//                        .password("{bcrypt}$2a$12$IGhbpbtOOESkDuc6MF2nPuLIi9vYf7YcaEnFQ6HMsJLAopQWUkg6K")
//                        .roles("USER","ADMIN")
//                        .build();
//        UserDetails userDetails3=
//                User.withUsername("ahmed")
//                        .password("{bcrypt}$2a$12$Zrv.3pAMarX9yVREnA6Ad.8xklNqpXRvE8qUfEzn5FUqh2z8KQ4yG")
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(userDetails1,userDetails2,userDetails3);
//    }
}
