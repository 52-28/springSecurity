package com.wildcodeschool.securityChallenge.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Bean                                                             
    public UserDetailsService userDetailsService() {
        // ensure the passwords are encoded properly
    	UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("Steve").password("motdepasse").roles("CHAMPION").build());
        manager.createUser(users.username("Nick").password("flerken").roles("DIRECTOR").build());
        return manager;
    }


    @Configuration    
    @Order(1) 
    public static class HomeLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            	.antMatcher("/")
                .httpBasic();

        }
    }
    
 
    @Configuration
    @Order(2)                                                        
    public static class AdminWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/secret-bases")                               
                .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                        .anyRequest().hasRole("DIRECTOR")
                )
                .httpBasic();
        }
    }

    
    @Configuration    
    @Order(3) 
    public static class ShampionLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            	.antMatcher("/avengers/assemble")
                .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .anyRequest().hasRole("CHAMPION")
            )
            .httpBasic();

        }
    }
    
}
