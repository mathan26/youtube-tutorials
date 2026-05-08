package com.techwithmathan.awscognito.aws_congnito_demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestOAuth2SecurityConfiguration.class)
class OrderControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicHealthEndpointIsAccessible() throws Exception {
        mockMvc.perform(get("/public/health"))
            .andExpect(status().isOk())
            .andExpect(content().string("Cognito demo is up"));
    }

    @Test
    void browserRequestsStartTheHostedUiLoginFlow() throws Exception {
        mockMvc.perform(get("/api/orders").accept(MediaType.TEXT_HTML))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", containsString("/oauth2/authorization/cognito")));
    }

    @Test
    void bearerTokensCanAccessOrders() throws Exception {
        mockMvc.perform(get("/api/orders")
                .with(jwt().jwt(jwt -> jwt.claim("email", "jwt-user@example.com"))))
            .andExpect(status().isOk())
            .andExpect(content().string("Orders for: jwt-user@example.com"));
    }

    @Test
    void hostedUiSessionsCanAccessOrders() throws Exception {
        mockMvc.perform(get("/api/orders")
                .with(oidcLogin().idToken(token -> token.claim("email", "browser-user@example.com"))))
            .andExpect(status().isOk())
            .andExpect(content().string("Orders for: browser-user@example.com"));
    }

    @Test
    void logoutRedirectsThroughCognitoLogout() throws Exception {
        mockMvc.perform(get("/logout").with(oidcLogin()))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string(
                "Location",
                containsString("https://example.test/logout?client_id=test-client-id")))
            .andExpect(header().string(
                "Location",
                containsString("logout_uri=http://localhost/login?logout")));
    }
}
