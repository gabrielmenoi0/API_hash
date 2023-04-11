package com.example.encurtadorurl.encurtador.url.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.ExecutionException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();


        /// Maneira manual de verificacao
//        PasswordEncoder passwordEncoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword + "apiHashs";
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return (rawPassword + "apiHashs").equals(encodedPassword);
//            }
//        };

    }
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("Menoi")
                .password(passwordEncoder().encode("123456@!"))
                .roles("USER");
    }
    protected  void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests().antMatchers("api/url/**").authenticated().and().httpBasic();
//                .and().csrf().disable().authorizeRequests().antMatchers("swagger-ui.html#/").permitAll();
    }
}
