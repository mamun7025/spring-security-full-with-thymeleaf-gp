package com.companyName.project.config;


import com.companyName.project.acl.jwt.config.JwtRequestAuthFilter;
import com.companyName.project.acl.springUser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringMultiWebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    @Order(1)
    public static class RestApiWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JwtRequestAuthFilter jwtAuthFilter;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class )
            ;

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }


    }



    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Order(2)
    public static class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImpl();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder);
            return authProvider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider());
        }


        private static final String[] AUTH_WHITELIST = {
                // -- Swagger UI v2
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                // other public endpoints
                "/h2-console/**",
        };

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                    .antMatchers("/auth/generate-token").permitAll()
                    .antMatchers("/h2-console", "/h2-console/**").permitAll() // don't use it in production
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .exceptionHandling().accessDeniedPage("/403")
            ;

            httpSecurity.csrf().disable();
            httpSecurity.headers().frameOptions().disable();


        }


        @Override
        public void configure(WebSecurity webSecurity) {

            webSecurity.ignoring().antMatchers(HttpMethod.GET,
                    "/favicon.ico",
                    "/*.html",
                    "/**/*.png",
                    "/**/*.PNG",
                    "/**/*.jpg",
                    "/**/*.woff2",
                    "/**/*.css.map",
                    "/**/*.js.map",
                    "/**/*.css",
                    "/**/*.js");

        }


    }




}