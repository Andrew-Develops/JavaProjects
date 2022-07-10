package com.registration.spring.persistence.util;

import com.registration.spring.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//folosim adnotarea @Configuration pentru a specifica ca este o clasa de configurare pentru spring
//folosim adnotarea @EnableWebSecurity care permite integrarea SpringSecurity in aplicatie
@Configuration
@EnableWebSecurity
//clasa WebSecurityConfigurerAdapter ofera metode care pot fi override pentru a ne crea propriul custom configuration folosit de SpringSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    //folosim adnotarea @Bean pentru a specifica ca este un bean care va fi injectat in aplicatie
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //folosim aceasta metoda pentru a cripta parola si apoi o punem in DaoAuthenticationProvider si o trimitem in baza de date
        return new BCryptPasswordEncoder();
    }

    //pentru a integra Spring data jpa si hibernate in SpringSecurity trebuie sa cream o metoda
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        //DaoAuthenticationProvider furnizeaza o metoda setPasswordEncoder(aici setam parola noastra)
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        //extindem UserDetailService in interfata noastra UserService
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //trebuie sa trecem parola din(authenticationProvider) in metoda config
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //folosim metoda authenticationProvider pentru a seta parola noastra
        auth.authenticationProvider(authenticationProvider());
    }

    //Override metoda si oferim toate configurarile pentru SpringSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //furnizam accesul catre diferite url-uri care ne intereseaza
        http.authorizeHttpRequests().antMatchers(
                        "/registration**",  //furnizam multiple url-uri de acces
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")    //configuram un custom url cu locul unde se afla login pagul nostru
                .permitAll()    //permitem oricarui user sa foloseasca url-ul "/login"
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();   //permitem tuturor sa foloseasca aceste linkuri
    }
}

