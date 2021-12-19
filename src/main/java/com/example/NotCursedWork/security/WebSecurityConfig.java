package com.example.NotCursedWork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    @Autowired
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(getPasswordEncoder())
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on ur.user_id=u.id where u.username=?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/reg", "/auth", "/index", "/gallery", "/review", "/gallery-georgins", "/gallery-roses", "/gallery-lutiks", "/about").permitAll()
                .antMatchers("/review/add", "/AboutRoses", "/AboutGeorgins", "/AboutLutiks").authenticated()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/auth")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout()
                .permitAll();
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(

                "/css/**",
                "/images/**"
        );
    }
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
  /* protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/reg", "/auth", "/index").permitAll()
                .antMatchers("/review").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout()w
                .permitAll();
    }
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/reg", "/auth", "/index", "/about").permitAll()
                .antMatchers("/review").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth")
                .loginProcessingUrl("/a")
                .defaultSuccessUrl("/homepage.html", true)
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout()
                .permitAll();
    }
   */