package com.example.secondAPI;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.test.annotation.Commit;

import com.example.secondAPI.Security.Repository.JpaRegisteredClientRepository;

@SpringBootTest
class UserMicroserviceApplicationTests {

	@Autowired
	private JpaRegisteredClientRepository jpaRegisteredClientRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	@Commit
	public void saveRegisteredClientToDB(){
		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("oidc-client")
				.clientSecret("$12$CVbOy4WwPMbqBLwZe/0u0ON7ahoPfzVUtoxGzh0Jk3DtKqlKcvnPC")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUri("https://oauth.pstmn.io/v1/callback")
				.postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
				.scope(OidcScopes.OPENID)
				.scope(OidcScopes.PROFILE)
				.scope("ADMIN")
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();

		jpaRegisteredClientRepository.save(oidcClient);

	}

}
