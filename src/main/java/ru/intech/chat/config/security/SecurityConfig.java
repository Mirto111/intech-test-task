package ru.intech.chat.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.intech.chat.service.UserService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/css/**").permitAll()
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/chat").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> {
                    form.loginPage("/login").permitAll();
                    form.defaultSuccessUrl("/chat", true);
                    form.failureUrl("/login?error=true");
                })
                .logout()
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
