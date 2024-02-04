/*
package com.example.studylist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@Lazy
@EnableGlobalMethodSecurity(securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class JwtSecurityConfig implements WebMvcConfigurer {

    private final JwtTokenFilter jwtTokenFilter;

    public JwtSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public SecurityFilterChain filterChainJwt(HttpSecurity http) throws Exception {
        http.csrf()
        .disable()
        .authorizeRequests()
         //Страницы доступные всем
        .antMatchers("/login", "swagger-ui.html")
        .permitAll()
        .and()
       .authorizeRequests()
        .antMatchers("/resources/**")
       .permitAll()

        .antMatchers("/records/*").hasRole("STUDENT")
        .antMatchers("/records/*", "/users/*").hasRole("TEACHER")

        .anyRequest().authenticated()
        .and()
        //Настройка для входа в систему
        .formLogin()
        .loginPage("/login")
        //Перенарпавление на главную страницу после успешного входа
        .defaultSuccessUrl("/records/show")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        //перенаправление после выхода
        .logoutSuccessUrl("/login");
    return http.build();


    }
}
*/
