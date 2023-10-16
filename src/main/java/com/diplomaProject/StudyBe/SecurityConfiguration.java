package com.diplomaProject.StudyBe;

import com.diplomaProject.StudyBe.configuration.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedList;
import java.util.List;

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

//                    auth.anyRequest().authenticated();
                    auth.requestMatchers( "/api/v1/auth/authentication", "/api/v1/auth/register").permitAll();
                    auth.requestMatchers(HttpMethod.OPTIONS).permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//    public DispatcherServlet dispatcherServlet() {
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        dispatcherServlet.setDispatchOptionsRequest(true);
//        return dispatcherServlet;
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        List<String> options = new LinkedList<>();
        options.add("GET");
        options.add("POST");
        options.add("PUT");
        options.add("DELETE");
        options.add("OPTIONS");
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.setAllowedMethods(options);
        source.registerCorsConfiguration("/**", config);


        return new CorsFilter(source);
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowCredentials(true);
//            }
//        };
//    }
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
