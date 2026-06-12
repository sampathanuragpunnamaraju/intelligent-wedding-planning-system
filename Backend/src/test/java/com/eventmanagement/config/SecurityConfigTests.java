package com.eventmanagement.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eventmanagement.security.JwtAccessDeniedHandler;
import com.eventmanagement.security.JwtAuthenticationEntryPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RestController;
@SpringBootTest(classes = SecurityConfigTests.TestApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityConfigTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void authEndpointsArePublic() throws Exception {
		mockMvc.perform(get("/api/v1/auth/login"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("public"));
	}

	@Test
	void apiEndpointsRequireAuthentication() throws Exception {
		mockMvc.perform(get("/api/v1/protected"))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.success").value(false))
				.andExpect(jsonPath("$.error.code").value("UNAUTHORIZED"))
				.andExpect(jsonPath("$.path").value("/api/v1/protected"));
	}

	@SpringBootConfiguration
	@EnableAutoConfiguration
	@Import({
			SecurityConfig.class,
			JwtAuthenticationEntryPoint.class,
			JwtAccessDeniedHandler.class,
			TestController.class
	})
	static class TestApplication {
	}

	@RestController
	@RequestMapping("/api/v1")
	static class TestController {

		@GetMapping("/auth/login")
		TestResponse publicEndpoint() {
			return new TestResponse("public");
		}

		@GetMapping("/protected")
		TestResponse protectedEndpoint() {
			return new TestResponse("protected");
		}
	}

	record TestResponse(String status) {
	}
}
