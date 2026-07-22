package com.smartcareer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcareer.response.Response;
import com.smartcareer.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class SecuriryConfig {

   private  final JwtAuthFilter jwtAuthFilter;
   private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception

                        .authenticationEntryPoint((request, response, ex) ->
                                writeErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                                        "Unauthorized"))

                        .accessDeniedHandler((request, response, ex) ->
                                writeErrorResponse(response, HttpServletResponse.SC_FORBIDDEN,
                                        "Forbidden")))


                .authorizeHttpRequests(auth -> auth
                        //For any user
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/learners/register",
                                "/api/v1/learners/login")
                        .permitAll()

                        //for Admin
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/learners/**")
                        .hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/learners/**")
                        .hasAuthority("ADMIN")

                        //for learner and admin
                        .requestMatchers(HttpMethod.PUT, "/api/v1/learners/**")
                        .hasAnyAuthority("ADMIN", "LEARNER")

                        //For all roles
                        .requestMatchers(HttpMethod.GET, "/api/v1/learners/**")
                        .hasAnyAuthority("ADMIN", "UNIVERSITY", "CAREER_ADVISOR", "LEARNER")


                        //Link to Destiny's ProgrammesApplicationController
                        //For programmes
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/applications/**")
                        .hasAuthority("UNIVERSITY")

                        .requestMatchers(HttpMethod.GET,"/api/v1/applications/**")
                        .hasAnyAuthority("ADMIN","LEARNER","UNIVERSITY","CAREER_ADVISOR")


                        // Link to CareerController
                         // Create a career
                        .requestMatchers(HttpMethod.POST, "/api/v1/careers/**")
                        .hasAnyAuthority("CAREER_ADVISOR")

                         // View careers
                        .requestMatchers(HttpMethod.GET, "/api/v1/careers/**")
                        .hasAnyAuthority("ADMIN", "LEARNER", "UNIVERSITY", "CAREER_ADVISOR")

                        // Update a career
                        .requestMatchers(HttpMethod.PUT, "/api/v1/careers/**")
                        .hasAuthority("CAREER_ADVISOR")

                         // Delete a career
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/careers/**")
                        .hasAnyAuthority("ADMIN","CAREER_ADVISOR" )

                        //profile
                        .requestMatchers(HttpMethod.GET, "/api/v1/learners/profile")
                        .hasAnyAuthority("ADMIN", "LEARNER", "UNIVERSITY", "CAREER_ADVISOR")

                        //
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard")
                        .hasAnyAuthority("LEARNER","ADMIN")

                        // Saved Programmes
                        .requestMatchers(HttpMethod.POST, "/api/v1/saved-programmes/**")
                        .hasAuthority("LEARNER")

                        .requestMatchers(HttpMethod.GET, "/api/v1/saved-programmes/**")
                        .hasAuthority("LEARNER")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/saved-programmes/**")
                        .hasAuthority("LEARNER")


                        // ==========================
                        // University Programmes
                        // ==========================

                        // Create Programme
                        .requestMatchers(HttpMethod.POST, "/api/v1/programmes")
                        .hasAnyAuthority("UNIVERSITY", "ADMIN")

                        // Update Programme
                        .requestMatchers(HttpMethod.PUT, "/api/v1/programmes/**")
                        .hasAnyAuthority("UNIVERSITY", "ADMIN")

                        // Delete Programme
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/programmes/**")
                        .hasAnyAuthority("UNIVERSITY", "ADMIN")

                        // View Programmes
                        .requestMatchers(HttpMethod.GET, "/api/v1/programmes/**")
                        .hasAnyAuthority("ADMIN", "LEARNER", "UNIVERSITY", "CAREER_ADVISOR")

                        //Everything else
                        .anyRequest()
                        .authenticated())


                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }




    //Helper method for error response
    private void writeErrorResponse(HttpServletResponse response,
                                    int status,
                                    String message) throws IOException {

        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Response<Void> body = Response.error(status, message);

        objectMapper.writeValue(response.getWriter(), body);
    }
}
