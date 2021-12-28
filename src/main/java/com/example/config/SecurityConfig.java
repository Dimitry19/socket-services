package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

import static com.example.constants.Constants.*;
//https://www.invivoo.com/securiser-application-spring-boot-spring-security/ TODO

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //declares which Page(URL) will have What access type
        http.authorizeRequests()
                .antMatchers(h2_path).permitAll()
                .antMatchers(home_path).permitAll()
                .antMatchers(welcome_path).authenticated()
                .antMatchers(admin_path).hasAnyAuthority("ADMIN")
                .antMatchers(user_path).hasAnyAuthority("USER")
                .antMatchers(manager_path).hasAnyAuthority("MANAGER")
                .antMatchers(common_path).hasAnyAuthority("USER","MANAGER")
                 // Any other URLs which are not configured in above antMatchers generally declared aunthenticated() in real time
                .anyRequest().authenticated()
                //Login form details
                .and().formLogin().defaultSuccessUrl(welcome_path,true)
                //Logout form details
                .and().logout().logoutRequestMatcher( new AntPathRequestMatcher(logout_path))
                //Exception Details
                .and()
                .exceptionHandling()
                .accessDeniedPage(denied_path);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)     //creates database connection
                .usersByUsernameQuery("select user_name,password,user_enabled from user where user_name=?")
                .authoritiesByUsernameQuery("select user_name,user_role from user where user_name=?")
                .passwordEncoder(passwordEncoder);
    }

    /**
     * Apply CORS configuration before Spring Security.
     * By default, "http.cors" take a bean called corsConfigurationSource.
     * @implNote https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#cors
     * @return a CORS configuration source.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
