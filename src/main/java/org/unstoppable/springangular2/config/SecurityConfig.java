package org.unstoppable.springangular2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username, password, true FROM users WHERE username=?")
//                .authoritiesByUsernameQuery("SELECT username, role FROM users WHERE username=?");
        auth.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ENCODING FILTER
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        http.addFilterBefore(encodingFilter, CsrfFilter.class);
        // PERMISSIONS
        http.authorizeRequests()
                .anyRequest().permitAll();
//                .and().formLogin().loginPage("/login").failureUrl("/login=error").defaultSuccessUrl("/").permitAll()
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
//                .and().csrf().ignoringAntMatchers("/websocket/**");
//                .and().headers().frameOptions().sameOrigin();
    }
}
