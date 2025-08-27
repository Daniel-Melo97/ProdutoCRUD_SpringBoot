package com.example.desafioNeurotech.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] ALLOWED_PATHS = {"/auth/**", //lista de Path's permitidos para acesso livre(rotas auth e swagger)
                                                    "/swagger-ui/**",
                                                    "/swagger-ui.html",
                                                    "/v3/api-docs/**",
                                                    "/swagger-resources/**",
                                                    "/webjars/**",
                                                    "/"};

    private final JwtAuthFilter jwtAuthFilter;
    //private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
        //this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//sem sessão no servidor
            .authorizeHttpRequests(auth -> auth.requestMatchers(ALLOWED_PATHS).permitAll().anyRequest().authenticated())//libera somente as rotas contidas na constante ALLOWED_PATHS
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);//Configura o filtro construído aqui como o que será utilizado para processar o JWT

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration) throws Exception {
      
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){//definição do algoritmo de hash de senha
        return new BCryptPasswordEncoder();
    }
    
}
