package sof3.hh.chatroom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
*/import org.springframework.security.web.SecurityFilterChain;

import sof3.hh.chatroom.web.UserDetailServiceImpl;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.ArrayList;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

@Autowired
private UserDetailServiceImpl userDetailsService;

@Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/","/login").permitAll()
        .requestMatchers(antMatcher("/index")).permitAll()
        .requestMatchers(antMatcher("/chatroomlist")).permitAll()
        .requestMatchers(antMatcher("/chatroom/*")).permitAll()
        .requestMatchers(antMatcher("/singup")).permitAll()
        .requestMatchers(antMatcher("/admin/**")).hasRole("ADMIN")
        .anyRequest().authenticated()        
      )
      .formLogin(formlogin -> formlogin
        .loginPage("/login")
        .defaultSuccessUrl("/chatroomlist", true)
        .permitAll()
      )
      .logout(logout -> logout
        .permitAll()
      );
      return http.build();
    }
/*@Bean
public UserDetailsService userDetailsService() {
    List<UserDetails> users = new ArrayList<UserDetails>();

    //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("user")
            .roles("USER")
            .build();

    users.add(user);

    UserDetails user2 = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("USER", "ADMIN")
            .build();
    
    users.add(user2);
    
    return new InMemoryUserDetailsManager(users);
}*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new
            BCryptPasswordEncoder());
    }
}
