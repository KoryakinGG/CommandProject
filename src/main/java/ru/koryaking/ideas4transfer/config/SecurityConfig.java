package ru.koryaking.ideas4transfer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.koryaking.ideas4transfer.domain.Role;
//import ru.rybinskov.ideas4transfer.service.user_service.UserService;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
//
//    @Autowired
//    DataSource dataSource;
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select u.username, u.password, 'true' from users_tbl u " +
//                        "where u.username=?")
//                .authoritiesByUsernameQuery("select u.username, u.role from users_tbl u " +
//                        "where u.username=?");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAuthority(Role.ADMIN.name())
                .antMatchers("/users", "/users/**").hasAnyAuthority(Role.ADMIN.name(), Role.BRAND_MANAGER.name(), Role.WAREHOUSE.name())
                .anyRequest().authenticated()
//
//                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/login")
                .loginProcessingUrl("/auth")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable();
//                .formLogin().disable();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").hasAnyAuthority(Role.ROLE_ADMIN.getRole(), Role.ROLE_BRAND_MANAGER.getRole(), Role.ROLE_WAREHOUSE.getRole())
//                .antMatchers("/users", "/users/**").hasAnyAuthority("ROLE_ADMIN", Role.ROLE_MANAGER.getRole(), Role.ROLE_WAREHOUSE.getRole())
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //      .loginPage("/login")
//                .loginProcessingUrl("/auth")
//                .failureUrl("/login-error")
//                .permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//                .and()
//                .csrf().disable();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
}
