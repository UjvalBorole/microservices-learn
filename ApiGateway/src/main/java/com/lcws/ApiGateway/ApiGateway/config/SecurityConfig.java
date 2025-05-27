package com.lcws.ApiGateway.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
//       httpSecurity
//               .authorizeExchange()
//               .anyExchange()
//               .authenticated()
//               .and()
//               .oauth2Client()
//               .and()
//               .oauth2ResourceServer()
//               .jwt();
//       return httpSecurity.build();

//    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                )
                .build();
    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/", "/auth/login").permitAll() // Allow access to login page
//                        .anyExchange().authenticated()
//                )
//                .oauth2Login(withDefaults()) // Add this for OAuth2 login
//                .oauth2Client(withDefaults())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(withDefaults())
//                )
//                .formLogin(login -> login
//                        .loginPage("/auth/login") // Your custom login page
//                )
//                .build();
//    }


//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/", "/auth/login").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2Login(withDefaults())  // Enable OAuth2 login
//                .oauth2Client(withDefaults())
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(withDefaults())
//                )
//                // Remove formLogin() unless you specifically need it
//                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Often needed for API gateways
//                .build();
//    }
}
