package com.eventmanagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eventmanagement.dto.api.ApiResponse;
import com.eventmanagement.exception.GlobalExceptionHandler;
import com.eventmanagement.exception.InvalidRequestException;
import com.eventmanagement.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(controllers = ControllerExceptionHandlingTests.TestApiFoundationController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({ GlobalExceptionHandler.class, ControllerExceptionHandlingTests.TestApiFoundationController.class })
class ControllerExceptionHandlingTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void validationFailuresUseStandardErrorEnvelope() throws Exception {
		mockMvc.perform(post("/test/api-foundation/validate")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"\"}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.success").value(false))
				.andExpect(jsonPath("$.data").doesNotExist())
				.andExpect(jsonPath("$.error.code").value("VALIDATION_FAILED"))
				.andExpect(jsonPath("$.error.message").value("Request validation failed"))
				.andExpect(jsonPath("$.error.fields[0].field").value("name"))
				.andExpect(jsonPath("$.path").value("/test/api-foundation/validate"));
	}

	@Test
	void domainNotFoundExceptionsUseStandardErrorEnvelope() throws Exception {
		mockMvc.perform(get("/test/api-foundation/not-found"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.success").value(false))
				.andExpect(jsonPath("$.error.code").value("RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.error.message").value("Test resource was not found"))
				.andExpect(jsonPath("$.path").value("/test/api-foundation/not-found"));
	}

	@Test
	void invalidRequestExceptionsUseStandardErrorEnvelope() throws Exception {
		mockMvc.perform(get("/test/api-foundation/invalid"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.success").value(false))
				.andExpect(jsonPath("$.error.code").value("INVALID_REQUEST"))
				.andExpect(jsonPath("$.error.message").value("Test request was invalid"))
				.andExpect(jsonPath("$.path").value("/test/api-foundation/invalid"));
	}

	@Test
	void successfulResponsesUseStandardEnvelope() throws Exception {
		mockMvc.perform(get("/test/api-foundation/success"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.data.status").value("ok"))
				.andExpect(jsonPath("$.error").doesNotExist())
				.andExpect(jsonPath("$.path").value("/test/api-foundation/success"));
	}

	@RestController
	@RequestMapping("/test/api-foundation")
	public static class TestApiFoundationController {

		@PostMapping("/validate")
		ApiResponse<Map<String, String>> validate(
				@Valid @RequestBody TestValidationRequest request,
				HttpServletRequest httpRequest
		) {
			return ApiResponse.success(Map.of("name", request.name()), httpRequest.getRequestURI());
		}

		@GetMapping("/not-found")
		ApiResponse<Void> notFound() {
			throw new ResourceNotFoundException("Test resource was not found");
		}

		@GetMapping("/invalid")
		ApiResponse<Void> invalid() {
			throw new InvalidRequestException("Test request was invalid");
		}

		@GetMapping("/success")
		ApiResponse<Map<String, String>> success(HttpServletRequest request) {
			return ApiResponse.success(Map.of("status", "ok"), request.getRequestURI());
		}
	}

	public record TestValidationRequest(@NotBlank String name) {
	}
}
