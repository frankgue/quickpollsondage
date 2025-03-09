/*
package com.gkfcsolution.quickpollsondage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigOld {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v1/**","/v2/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                .antMatchers("/v3/polls/**").authenticated()
                .and()
                .httpBasic()
                .realmName("Quick Poll")
                .and()
                .csrf()
                .disable();




        return http.build();
    }

}
*/
