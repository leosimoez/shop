package br.com.lm.shop.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import static org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;


@Configuration
// We have to enable this manually as I excluded auto config for security
@EnableWebSecurity
// Yeah, more Annotations!
@EnableGlobalMethodSecurity(prePostEnabled = true)
// enable the above property class
@EnableConfigurationProperties(KeycloakClientProperties.class)
class SecurityConfig {

    /**
     * This repository contains all known client registrations. THis is only one-
     * Fan-out to different clients is done by Keycloak if necessary.
     *
     * @param clientProperties
     * @return
     */
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(
            final KeycloakClientProperties clientProperties
    ) {
        return new InMemoryClientRegistrationRepository(
            clientProperties.asClientRegistration());
    }

    /**
     * Configures OAuth Login with Spring Security 5.
     * @param clientProperties
     * @return
     */
    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurer(
        final KeycloakClientProperties clientProperties
    ) {
        return new WebSecurityConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http
                    // Configure session management to your needs.
                    // I need this as a basis for a classic, server side rendered application
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .and()
                    // Depends on your taste. You can configure single paths here
                    // or allow everything a I did and then use method based security
                    // like in the controller below
                    .authorizeRequests()                       
                        .anyRequest().permitAll()
                        .and()
                    // This is the point where OAuth2 login of Spring 5 gets enabled
                    .oauth2Login()
                        // I don't want a page with different clients as login options
                        // So i use the constant from OAuth2AuthorizationRequestRedirectFilter
                        // plus the configured realm as immediate redirect to Keycloak
                        .loginPage(DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/" + clientProperties.getRealm());
            }
        };
    }    
}
