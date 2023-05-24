package com.tms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailService clientDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/user/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/user/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/user/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/transport").permitAll()
                .antMatchers(HttpMethod.GET, "/transport/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/transport/fromUser/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/transport/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transport").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/transport/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/transport/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/transport/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/transport/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cargo").permitAll()
                .antMatchers(HttpMethod.GET, "/cargo/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/cargo/fromUser/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/cargo/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/cargo").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/cargo/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/cargo/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cargo/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/cargo/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/reviews").permitAll()
                .antMatchers(HttpMethod.GET, "/reviews/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/reviews").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT, "/reviews/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/reviews/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/reviews/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/favorite/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/favorite/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/favorite/**").hasRole("USER")
                .antMatchers("/swagger/**").permitAll()
                .antMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
