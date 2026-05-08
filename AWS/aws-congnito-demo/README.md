# AWS Cognito Demo With Spring Boot

This project shows how to integrate an AWS Cognito User Pool with a Spring Boot application.

It supports two authentication styles:

- Browser login through Cognito Hosted UI
- API access using `Authorization: Bearer <token>`

The app protects `/api/**`, leaves `/public/**` open, and demonstrates logout from both the local Spring session and the Cognito Hosted UI session.

## What This Project Demonstrates

- Redirecting unauthenticated browser users to Cognito Hosted UI
- Handling the OAuth 2.0 authorization code flow in Spring Security
- Validating Cognito JWTs as a resource server
- Reading logged-in user details from the authenticated principal
- Supporting both browser session login and Bearer token API access
- Redirecting logout through Cognito so the Hosted UI session is cleared too

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Security
- Spring OAuth2 Client
- Spring OAuth2 Resource Server
- AWS Cognito User Pool

## Project Structure

```text
src/main/java/com/techwithmathan/awscognito/aws_congnito_demo
|- AwsCongnitoDemoApplication.java
|- config
|  |- SecurityConfig.java
|  |- CognitoLogoutSuccessHandler.java
|- controller
   |- PublicController.java
   |- OrderController.java

src/main/resources
|- application.yaml

src/test/java/com/techwithmathan/awscognito/aws_congnito_demo
|- AwsCongnitoDemoApplicationTests.java
|- OrderControllerIntegrationTests.java
|- TestOAuth2SecurityConfiguration.java
```

## Key Files

### `SecurityConfig`

`src/main/java/com/techwithmathan/awscognito/aws_congnito_demo/config/SecurityConfig.java`

This is the main Spring Security setup.

Responsibilities:

- allows `/public/**` without authentication
- requires authentication for everything else
- redirects browser users to Cognito login
- enables JWT validation for Bearer token requests
- configures logout through Cognito

### `CognitoLogoutSuccessHandler`

`src/main/java/com/techwithmathan/awscognito/aws_congnito_demo/config/CognitoLogoutSuccessHandler.java`

This builds the Cognito logout URL and redirects the browser there after the local Spring session is cleared.

Without this, clearing the local session alone is not enough because Cognito may still keep the Hosted UI session active.

### `OrderController`

`src/main/java/com/techwithmathan/awscognito/aws_congnito_demo/controller/OrderController.java`

Protected endpoint:

- `GET /api/orders`

This endpoint works in both modes:

- browser session login from Cognito Hosted UI
- direct Bearer token authentication

It tries to resolve the user in this order:

- `email`
- `cognito:username`
- `sub`
- authenticated principal name

### `PublicController`

`src/main/java/com/techwithmathan/awscognito/aws_congnito_demo/controller/PublicController.java`

Public endpoint:

- `GET /public/health`

Use this to confirm the app is up without logging in.

### `application.yaml`

`src/main/resources/application.yaml`

This file configures:

- Cognito region
- user pool id
- app client id
- app client secret
- OAuth2 client registration
- resource server issuer URI

## Authentication Modes

### 1. Browser Login Through Hosted UI

This flow is used when someone opens the app in a browser.

Example:

1. Browser requests `GET /api/orders`
2. Spring Security sees the request is protected
3. User is redirected to `/oauth2/authorization/cognito`
4. Spring redirects to Cognito Hosted UI
5. User logs in
6. Cognito redirects back to `/login/oauth2/code/cognito?code=...&state=...`
7. Spring exchanges the authorization code for tokens
8. Spring creates a logged-in session
9. The user is redirected back to the protected page
10. `OrderController` returns the response

Important detail:

- the browser receives a redirect with an authorization code
- Spring Boot performs the token exchange server-side
- tokens are not exposed in the browser URL in this flow

### 2. Bearer Token API Access

This flow is used by APIs, Postman, mobile apps, SPAs, or other clients.

Example:

```http
GET /api/orders
Authorization: Bearer <access_token>
```

Spring validates the JWT using Cognito metadata and public keys.

If valid, the request is authenticated and reaches `OrderController`.

## Why Both Modes Matter

Real applications often need both:

- browser users sign in through a UI
- frontend apps or service clients call backend APIs with tokens

This project shows how one Spring Boot app can support both patterns using the same Cognito User Pool.

## Cognito Configuration

The project uses these properties:

```yaml
app:
  cognito:
    region: ${COGNITO_REGION:ap-south-1}
    user-pool-id: ${COGNITO_USER_POOL_ID:...}
    client-id: ${COGNITO_CLIENT_ID:...}
    client-secret: ${COGNITO_CLIENT_SECRET:...}
```

Spring then uses them here:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://cognito-idp.${app.cognito.region}.amazonaws.com/${app.cognito.user-pool-id}
      client:
        registration:
          cognito:
            client-id: ${app.cognito.client-id}
            client-secret: ${app.cognito.client-secret}
```

## Running The Application

### Prerequisites

- Java 21
- Maven
- AWS Cognito User Pool
- Cognito Hosted UI domain configured
- App client configured for authorization code flow

### Start the app

```bash
mvn spring-boot:run
```

Or:

```bash
mvnw.cmd spring-boot:run
```

### Useful endpoints

- `http://localhost:8080/public/health`
- `http://localhost:8080/api/orders`
- `http://localhost:8080/logout`

## Testing The Full Flow

### Browser flow

1. Open `http://localhost:8080/api/orders`
2. You should be redirected to Cognito Hosted UI
3. Sign up or sign in
4. After successful login, you should be redirected back
5. The app should return a response like:

```text
Orders for: your-email@example.com
```

### Bearer token flow

Use a valid Cognito access token:

```bash
curl -H "Authorization: Bearer YOUR_ACCESS_TOKEN" http://localhost:8080/api/orders
```

## Logout Behavior

Calling:

```text
http://localhost:8080/logout
```

does two things:

1. clears the local Spring Security session
2. redirects to Cognito `/logout` so the Hosted UI session is cleared

This is important because clearing only the local `JSESSIONID` is often not enough. If Cognito still has an active session, the user may be logged in again automatically on the next redirect.

## How Multiple Users Are Handled

This app supports multiple users.

### Browser login

Each browser session gets its own:

- `JSESSIONID`
- authenticated principal
- tokens stored in that user session

### Bearer token access

Each request is authenticated independently using the token sent in the request header.

## How To Debug The Login Flow

### Browser network debugging

Open browser DevTools and enable `Preserve log`.

You will typically see:

1. `GET /api/orders`
2. redirect to `/oauth2/authorization/cognito`
3. redirect to Cognito `/oauth2/authorize`
4. redirect back to `/login/oauth2/code/cognito?code=...&state=...`
5. final redirect back to the original page

### Spring logs

Add this temporarily to `application.yaml`:

```yaml
logging:
  level:
    org.springframework.security: TRACE
    org.springframework.security.oauth2: TRACE
    org.springframework.web: DEBUG
```

This helps you see:

- which filters are handling requests
- when redirects are created
- when the code is exchanged for tokens
- when authentication is saved in the session

### Useful framework classes to debug

If your IDE downloads Spring sources, place breakpoints in:

- `OAuth2AuthorizationRequestRedirectFilter`
- `OAuth2LoginAuthenticationFilter`
- `OAuth2AuthorizationCodeAuthenticationProvider`
- `OidcAuthorizationCodeAuthenticationProvider`

If you want to inspect token handling after login, these types are especially useful:

- `OAuth2AuthorizedClient`
- `OidcUser`

## Real-World Use Cases

This pattern is common in real production systems:

- Admin portals using Hosted UI
- E-commerce sites showing logged-in user data
- Mobile apps using Cognito access tokens to call Spring APIs
- Single page apps that store access tokens and call a backend
- Microservices that validate Cognito JWTs on every request

Typical division of responsibility:

- Cognito handles sign-up, sign-in, password reset, identity, and token issuance
- Spring Security handles protected routes and token validation
- business controllers handle only business logic

## Integration Tests

The tests verify:

- public endpoint access
- browser redirect into the Hosted UI flow
- Bearer token access
- hosted UI session access
- logout redirect through Cognito logout

Main test file:

- `src/test/java/com/techwithmathan/awscognito/aws_congnito_demo/OrderControllerIntegrationTests.java`

## Important Notes

### 1. Use environment variables in real environments

For production, do not hardcode Cognito values in source files.

Prefer:

- `COGNITO_REGION`
- `COGNITO_USER_POOL_ID`
- `COGNITO_CLIENT_ID`
- `COGNITO_CLIENT_SECRET`

### 2. Access token vs ID token

- `access_token` is usually used to call APIs
- `id_token` is mainly for user identity details

### 3. Logout does not revoke already-issued tokens

Browser logout clears the session and Hosted UI session, but an already-issued access token may still remain valid until expiry unless token revocation is added separately.

## Suggested Improvements

- add a temporary debug endpoint to inspect tokens for local learning
- add role or group-based authorization
- add Google login through Cognito social identity provider
- move secrets fully out of `application.yaml`
- add refresh token handling examples
- add frontend UI instead of plain text responses

## Summary

This project is a practical reference for:

- Cognito Hosted UI login with Spring Boot
- authorization code flow in Spring Security
- Cognito JWT validation as a resource server
- supporting both browser users and API clients
- proper logout handling across Spring and Cognito

If you are learning Spring Security with Cognito, this repository is a good minimal end-to-end example.
