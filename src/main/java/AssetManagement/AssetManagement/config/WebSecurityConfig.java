/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author Okala
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler successHandler;
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
//    @Value("${spring.queries.users-query}")
//    private String SQL_LOGIN;
//    
//    @Value("${spring.queries.roles-query}")
//    private String SQL_ROLE;
    
    @Autowired
    private DataSource dataSource;

    private static final String SQL_ROLE
            = "SELECT a.username as username, r.name as authority "
            + "FROM account a "
            + "INNER JOIN employee_role er "
            + "ON a.id = er.employee "
            + "INNER JOIN role r "
            + "ON er.role = r.id "
            + "WHERE a.username = ?";

    private static final String SQL_LOGIN
            = "SELECT username as username, password, '1' as active "
            + "FROM account "
            + "WHERE is_delete = 'false' AND username = ?";

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl userDetails = new JdbcDaoImpl();
        userDetails.setDataSource(dataSource);
        userDetails.setUsersByUsernameQuery(SQL_LOGIN);
        userDetails.setAuthoritiesByUsernameQuery(SQL_ROLE);
        
        return userDetails;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/forget/**", "/confirm/**", "/AccountActivation/**").permitAll() //bintang 1 permit ALL
                .antMatchers("/dist/**").permitAll()                               //bintang 2 permit ALL must Login
                .antMatchers("/home/**").hasAnyAuthority("Admin")
                .antMatchers("/employee/**").hasAnyAuthority("Employee")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("email")
                .successHandler(successHandler)
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
    
}
