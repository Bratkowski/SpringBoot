package com.bratkowski.booklibary.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers(
                        "/register/**",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**").permitAll().
                and().authorizeRequests()
                .antMatchers("/").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/books").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/books/hires/**").hasAnyAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/books")
                .and().exceptionHandling().accessDeniedPage("/acces-denied")
                .and().httpBasic();

        httpSecurity.authorizeRequests()
                .antMatchers("/api").hasAnyAuthority("ADMIN", "DEV");

        /*httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");*/
    }

    @Autowired
    public void securityUsers(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT USER_NAME, PASSWORD, ENABLED FROM USER u WHERE USER_NAME = ?")
                .authoritiesByUsernameQuery("SELECT USER_NAME, NAME FROM USER u JOIN ROLE r ON u.ID = r.USERID WHERE u.USER_NAME = ?");
    }

}
