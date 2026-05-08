package com.techwithmathan.awscognito.aws_congnito_demo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@TestConfiguration(proxyBeanMethods = false)
public class TestOAuth2SecurityConfiguration {

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration cognito = ClientRegistration.withRegistrationId("cognito")
            .clientId("test-client-id")
            .clientSecret("test-client-secret")
            .clientName("Cognito")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("openid", "email", "profile")
            .issuerUri("https://example.test")
            .authorizationUri("https://example.test/oauth2/authorize")
            .tokenUri("https://example.test/oauth2/token")
            .jwkSetUri("https://example.test/oauth2/jwks")
            .userInfoUri("https://example.test/oauth2/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .build();

        return new InMemoryClientRegistrationRepository(cognito);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return token -> Jwt.withTokenValue(token)
            .header("alg", "none")
            .claim(IdTokenClaimNames.SUB, "test-user")
            .build();
    }
}
