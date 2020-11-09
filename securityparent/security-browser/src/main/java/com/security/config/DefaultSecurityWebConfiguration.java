package com.security.config;

import com.security.core.filter.ValidateCodeFilter;
import com.security.core.properties.SecurityProperties;
import com.security.handler.DefaultAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author heartccace
 * @create 2020-05-29 14:05
 * @Description 返回网页形式
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class DefaultSecurityWebConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    @Qualifier("DefaultAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    @Qualifier("DefaultAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    @Qualifier("DefaultUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("validateCodeFilter")
    private ValidateCodeFilter codeFilter;

    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(codeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/index.html")
                    .loginProcessingUrl("/authentication/login")
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(userDetailsService)
                    .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
                    .and()
                .authorizeRequests()
                    .antMatchers("/index.html", securityProperties.getBrowserProperties().getLoginPage(),"/code/image")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .csrf()
                    .disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**",
                "/vendor/**","/js/**",
                "/fonts/**","/images/**");
    }
}
