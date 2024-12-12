package com.example.teste.da.join.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    // Definindo o usuário em memória
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")  // Nenhuma necessidade de especificar roles se for um simples usuário
                    .build()
        );
    }

    // Configuração do filtro de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desabilita CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**").permitAll() // Permite login e recursos estáticos
                .anyRequest().authenticated() // Requer autenticação para qualquer outra rota
            )
            .formLogin(login -> login
                
                .successHandler(authenticationSuccessHandler()) // Redirecionamento após login
                .permitAll() // Permite acesso público ao login
            )
            .logout(logout -> logout.permitAll()); // Permite logout sem autenticação

        return http.build();
    }

    // Manipulador de sucesso no login
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/api/categorias/"); // Redireciona para o controlador após login
        };
    }

    // Bean para o encoder de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
