package com.techwithmathan.awscognito.aws_congnito_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    @GetMapping("/orders")
    public ResponseEntity<String> getOrders(Authentication authentication) {
        String username = resolveUserIdentifier(authentication);
        return ResponseEntity.ok("Orders for: " + username);
    }

    private String resolveUserIdentifier(Authentication authentication) {
        if (authentication == null) {
            return "anonymous";
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt) {
            return firstNonBlank(
                jwt.getClaimAsString("email"),
                jwt.getClaimAsString("cognito:username"),
                jwt.getSubject(),
                authentication.getName()
            );
        }

        if (principal instanceof OidcUser oidcUser) {
            return firstNonBlank(
                oidcUser.getEmail(),
                oidcUser.getPreferredUsername(),
                oidcUser.getSubject(),
                authentication.getName()
            );
        }

        if (principal instanceof OAuth2AuthenticatedPrincipal oauth2Principal) {
            return firstNonBlank(
                oauth2Principal.getAttribute("email"),
                oauth2Principal.getAttribute("preferred_username"),
                authentication.getName()
            );
        }

        return authentication.getName();
    }

    private String firstNonBlank(Object... values) {
        for (Object value : values) {
            if (value instanceof String text && StringUtils.hasText(text)) {
                return text;
            }
        }
        return "unknown-user";
    }
}
