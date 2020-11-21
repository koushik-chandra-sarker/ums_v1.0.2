package bd.edu.seu.ums.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","css/*","js/*").permitAll()
                .antMatchers(HttpMethod.GET,"/management/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/management/api/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/management/api/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/management/api/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasAuthority("student:write")
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority("student:write")
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority("student:write")
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority("student:write")
                .antMatchers("/management/api/**").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().httpBasic();
    }

}
