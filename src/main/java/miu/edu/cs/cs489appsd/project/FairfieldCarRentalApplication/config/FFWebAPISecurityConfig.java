package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.config;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.filter.JWTAuthFilter;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl.FFUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class FFWebAPISecurityConfig {
    private FFUserDetailsService ffUserDetailsService;
    private JWTAuthFilter jwtAuthFilter;

    public FFWebAPISecurityConfig(FFUserDetailsService ffUserDetailsService, JWTAuthFilter jwtAuthFilter) {
        this.ffUserDetailsService = ffUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/ffweb/api/v1/public/auth/**").permitAll()
                                    .requestMatchers("/images/**").permitAll()
                                    .requestMatchers("/css/**").permitAll()
                                    .requestMatchers("/js/**").permitAll()
                                    .requestMatchers("/login/**").permitAll()
                                    .requestMatchers("/public/**").permitAll()
                                    .requestMatchers("/ffweb/api/v1/customers/**").authenticated()
                                    .requestMatchers("/ffweb/api/v1/owners/**").authenticated()
                                    .requestMatchers("/ffweb/api/v1/bookings/**").authenticated()
                                    .requestMatchers("/ffweb/api/v1/cars/**").authenticated();
                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(configurer -> configurer
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(ffUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
