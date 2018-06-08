package br.com.lm.shop.config;

import java.util.Collections;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import lombok.Data;

/**
 * Basic property class that wraps up all properties relevant
 * to one Keycloak realm.
 */
@Data
@ConfigurationProperties(prefix = "keycloak-client")
class KeycloakClientProperties {
    private String id;

    private String secret;

    private String name;

    private Set<String> scopes = Collections.emptySet();

    private String serverUrl;

    private String realm;

    /**
     * And this is the only interesting part here. The keycloak realm
     * is transformed to a so called ClientRegistration. ClientRegistrations
     * are used by Spring Security 5 to define different OAuth providers
     * @return
     */
    public ClientRegistration asClientRegistration() {
        final String openIdConnectBaseUri
            = this.serverUrl + "/realms/" + this.realm + "/protocol/openid-connect";
        return ClientRegistration.withRegistrationId(this.realm)
            .clientId(this.id)
            .clientSecret(this.secret)
            .clientName(this.name)
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope(this.scopes.toArray(new String[0]))
            .authorizationUri(openIdConnectBaseUri + "/auth")
            .tokenUri(openIdConnectBaseUri + "/token")
            .jwkSetUri(openIdConnectBaseUri + "/certs")
            .userInfoUri(openIdConnectBaseUri + "/userinfo")
            // Use a sane username from the JWT
            .userNameAttributeName("preferred_username")
            .build();
    }
}