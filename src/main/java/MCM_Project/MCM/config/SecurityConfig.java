package MCM_Project.MCM.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    // @Bean
    // public securityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/**").permitAll() // Allow all API access
    //             .anyRequest().authenticated());

    //     return http.build();
    // }
}
