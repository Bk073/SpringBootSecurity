package com.shopping.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder; 

import com.shopping.enitites.User;
import com.shopping.repository.UserRepository;
import com.shopping.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true) 
@EnableWebSecurity 
@EnableJpaRepositories(basePackageClasses = UserRepository.class) 
@Configuration 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { 
 
 
    @Autowired 
    private CustomUserDetailsService userDetailsService;
 
    @Override 
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 
        auth.userDetailsService(userDetailsService);
//        .passwordEncoder(getPasswordEncoder()); 
    } 
 
 
    @Override 
    protected void configure(HttpSecurity http) throws Exception {
 
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/admin").hasAuthority("ADMIN")
                .anyRequest().fullyAuthenticated()
                .anyRequest().permitAll() 
                .and() 
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login"); 
    } 
 
    /*private PasswordEncoder getPasswordEncoder() { 
        return new PasswordEncoder() { 
            @Override 
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            } 
 
            @Override 
            public boolean matches(CharSequence charSequence, String s) {
                return true; 
            } 
        }; 
    } */
} 