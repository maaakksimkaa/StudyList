package com.example.studylist.config;

import com.example.studylist.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(CustomUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/user/registration",
            "/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем защиту CSRF для упрощения разработки и тестирования
                .authorizeRequests()
                // Страницы, доступные всем
                .antMatchers(AUTH_WHITELIST).permitAll()
                // Настройка доступа к ресурсам
                .antMatchers("/records/**").hasRole("STUDENT") // Доступ к /records/** только для STUDENT
                .antMatchers("/users/**", "/records/**").hasRole("TEACHER") // Доступ к /users/** только для TEACHER
                // Указываем, что запросы по адресам /records/** доступны для TEACHER, но это правило уже не нужно,
                // так как оно создает путаницу и потенциально конфликтует с предыдущими правилами.
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                .and()
                // Настройка для входа в систему
                .formLogin()
                .loginPage("/login") // Указываем страницу входа
                .defaultSuccessUrl("/") // Перенаправление на главную страницу после успешного входа
                .permitAll() // Разрешаем доступ к форме входа всем
                .and()
                .logout()
                .permitAll() // Разрешаем доступ к процедуре выхода всем
                .logoutSuccessUrl("/login"); // Перенаправление после выхода на страницу входа

        return http.build();

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


}
