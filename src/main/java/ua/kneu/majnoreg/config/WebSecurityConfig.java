package ua.kneu.majnoreg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         return httpSecurity
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(requests -> requests
                        // .requestMatchers("/users/**")
                        //    .hasAnyRole("Наглядач","Адміністратор", "Менеджер", "Користувач")
                         .requestMatchers("/auth/**").permitAll()
                         .requestMatchers("/","/declarations/**", "/users/**", "/error/*", "/about")
                            .permitAll()
                         .anyRequest().authenticated()
                 )
                 .formLogin(form -> form.loginPage("/auth/login").usernameParameter("login").permitAll())
                 .logout(logout -> logout.logoutUrl("/auth/logout").logoutSuccessUrl("/"))
                 .build();
     }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
