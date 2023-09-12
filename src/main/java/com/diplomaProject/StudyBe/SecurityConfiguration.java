package com.diplomaProject.StudyBe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
//                .requiresChannel(channel ->
//                        channel.anyRequest().requiresSecure())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.anyRequest().permitAll();
//                    auth.requestMatchers("/auth/").permitAll();
//                    auth.requestMatchers("/admin/").hasRole("ADMIN");
//                    auth.requestMatchers("/user/**").hasAnyRole("ADMIN","USER");
//                    auth.anyRequest().authenticated();
                })
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
//                        )
//                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
