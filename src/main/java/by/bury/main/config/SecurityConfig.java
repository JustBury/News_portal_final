package by.bury.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // add a reference to our data source

    @Autowired
    private DataSource securityDataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(securityDataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user WHERE username = ?")
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/getNews/**").hasAnyRole("GUEST", "MODERATOR", "ADMIN")
                .antMatchers("/addNewNews/**").hasAnyRole("GUEST", "MODERATOR", "ADMIN")
                .antMatchers("/getNews/**").hasAnyRole("GUEST", "MODERATOR", "ADMIN")
                .antMatchers("/updateNews/**").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole( "ADMIN")
                .antMatchers("/deleteUser/**").hasAnyRole( "ADMIN")
                .antMatchers("/deleteNews/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
         //       .loginPage("/showMyLoginPage") // – the custom login page
                .loginProcessingUrl("/authenticateTheUser") // – the URL to submit the username and password to
                .permitAll()

                .and()

                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        super.setAuthenticationConfiguration(authenticationConfiguration);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}






