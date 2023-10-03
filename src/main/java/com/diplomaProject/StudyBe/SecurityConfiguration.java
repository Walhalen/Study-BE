package com.diplomaProject.StudyBe;

import com.diplomaProject.StudyBe.configuration.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;



    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->{
//                    auth.anyRequest().permitAll();
//                    auth.requestMatchers("/auth/").permitAll();
//                    auth.requestMatchers("/admin/").hasRole("ADMIN");
//                    auth.requestMatchers("/user/**").hasAnyRole("ADMIN","USER");
//                    auth.anyRequest().authenticated();
                    auth.requestMatchers("/api/v1/auth/register", "/api/v1/auth/authentication").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");  // Replace with your frontend's URL
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
////                .requiresChannel(channel ->
////                        channel.anyRequest().requiresSecure())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth ->{
//                    auth.anyRequest().permitAll();
////                    auth.requestMatchers("/auth/").permitAll();
////                    auth.requestMatchers("/admin/").hasRole("ADMIN");
////                    auth.requestMatchers("/user/**").hasAnyRole("ADMIN","USER");
////                    auth.anyRequest().authenticated();
//                })
////                .oauth2ResourceServer(oauth2 -> oauth2
////                        .jwt(jwt -> jwt
////                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
////                        )
////                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }


}
