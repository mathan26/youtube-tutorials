package com.techwithmathan.awscognito.aws_congnito_demo.config;

import java.io.IOException;
import java.net.URI;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

public class CognitoLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final String registrationId;

    public CognitoLogoutSuccessHandler(
            ClientRegistrationRepository clientRegistrationRepository,
            String registrationId) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.registrationId = registrationId;
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        ClientRegistration clientRegistration =
            this.clientRegistrationRepository.findByRegistrationId(this.registrationId);

        if (clientRegistration == null) {
            response.sendRedirect("/login?logout");
            return;
        }

        URI authorizationUri = URI.create(clientRegistration.getProviderDetails().getAuthorizationUri());
        String applicationLogoutRedirect = newBaseUrlBuilder(request)
            .path(request.getContextPath())
            .path("/login")
            .queryParam("logout")
            .build()
            .toUriString();

        String cognitoLogoutUrl = UriComponentsBuilder.newInstance()
            .scheme(authorizationUri.getScheme())
            .host(authorizationUri.getHost())
            .port(authorizationUri.getPort())
            .path("/logout")
            .queryParam("client_id", clientRegistration.getClientId())
            .queryParam("logout_uri", applicationLogoutRedirect)
            .build()
            .toUriString();

        response.sendRedirect(cognitoLogoutUrl);
    }

    private UriComponentsBuilder newBaseUrlBuilder(HttpServletRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
            .scheme(request.getScheme())
            .host(request.getServerName());

        if (!isDefaultPort(request.getScheme(), request.getServerPort())) {
            builder.port(request.getServerPort());
        }

        return builder;
    }

    private boolean isDefaultPort(String scheme, int port) {
        return ("http".equalsIgnoreCase(scheme) && port == 80)
            || ("https".equalsIgnoreCase(scheme) && port == 443);
    }
}
