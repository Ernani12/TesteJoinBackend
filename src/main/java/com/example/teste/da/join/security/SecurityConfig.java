package com.example.teste.da.join.security;

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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
            .requestMatchers("/api/auth/login", "/css/**", "/js/**", "/h2-console/**").permitAll() // Permite login, recursos estáticos e H2 Console
            .anyRequest().authenticated() // Requer autenticação para outras rotas
            )
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
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // Permite todas as origens
        configuration.addAllowedMethod("*");       // Permite todos os métodos (GET, POST, etc.)
        configuration.addAllowedHeader("*");       // Permite todos os cabeçalhos
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configuração global de CORS para todos os endpoints
                registry.addMapping("/**") // Permite CORS para todos os endpoints
                        .allowedOrigins("http://localhost:4200") // Permite apenas esta origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite métodos necessários
                        .allowedHeaders("*") // Permite todos os cabeçalhos
                        .allowCredentials(true); // Permite credenciais como cookies, headers autorizados
            }
        };
    }
}
