package com.techwithmathan.awscognito.aws_congnito_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestOAuth2SecurityConfiguration.class)
class AwsCongnitoDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
