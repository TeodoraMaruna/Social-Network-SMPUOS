package com.postservice.config;

import com.postservice.security.TokenUtils;
import com.postservice.security.auth.RestAuthenticationEntryPoint;
import com.postservice.security.auth.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/**/*.html",
                "/**/*.css", "/**/*.js");

        web.ignoring().antMatchers(HttpMethod.GET, "/find-all-posts/user/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/image/**");
        // web.ignoring().antMatchers(HttpMethod.DELETE, "/api/houseReservations/**");
       // web.ignoring().antMatchers(HttpMethod.GET, "/findAll");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
//                .antMatchers("api/user/**").hasRole("USER")
                .anyRequest()
                .authenticated().and()
                .cors().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils),
                        BasicAuthenticationFilter.class)
                .httpBasic();
        http.csrf().disable();
    }

}
