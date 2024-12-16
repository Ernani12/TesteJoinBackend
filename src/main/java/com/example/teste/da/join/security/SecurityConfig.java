package com.example.teste.da.join.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")
                    .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desabilita CSRF (necessário para APIs REST)
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/categorias/**").permitAll() // Liberar o endpoint
            .requestMatchers("/api/categorias/listar").permitAll() // Permite acesso ao endpoint de categorias
            .requestMatchers("/api/categorias/SalvarC").permitAll() // Permite acesso ao endpoint de categorias
            .requestMatchers("/api/categorias/deleteC/**").permitAll() // Permitir acesso ao endpoint DELETE
            .requestMatchers("/api/categorias/listarid/**").permitAll() // Permitir acesso ao endpoint DELETE
            .requestMatchers("/api/categorias/updateC/**").permitAll() // Permitir acesso ao endpoint DELETE
            .requestMatchers("/api/produtos/listar").permitAll() // Permite acesso ao endpoint de categorias
            .requestMatchers("/api/produtos/listarid/**").permitAll() // Permitir acesso ao endpoint DELETE
            .requestMatchers("/api/produtos/**").permitAll() // Liberar o endpoint
            .requestMatchers("/api/produtos/salvarP").permitAll() // Permite acesso ao endpoint de categorias
            .requestMatchers("/api/produtos/deleteP/**").permitAll() // Permitir acesso ao endpoint DELETE
            .requestMatchers("/api/produtos/updateP/**").permitAll() // Permitir acesso ao endpoint DELETE
            .requestMatchers("/api/auth/login/**", "/css/**", "/js/**", "/h2-console/**",
                                 "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html/",
                                 "/webjars/**").permitAll() // Permite acesso a rotas públicas
                .anyRequest().authenticated() // Requer autenticação para outras rotas
            )
            .cors() // Ativa o suporte a CORS
            .and()
            .headers(headers -> headers.disable()) // Desativa configurações de cabeçalhos
            .logout(logout -> logout.permitAll()); // Permite logout público

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Permite apenas o frontend
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Cabeçalhos permitidos
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Aplica configuração a todos os endpoints

        return source;
    }
}
