package org.yokekhei.fsd.capstone.api;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = FoodBoxApplication.class)
public class FeeControllerTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test(priority = 0)
	public void testGetFeeById() throws Exception {
		mockMvc.perform(get("/api/fee/{type}", "delivery"))
				.andExpect(jsonPath("$.type").value("delivery"))
				.andExpect(jsonPath("$.value").value(5.0));
	}

	@Test(priority = 1)
	public void testGetFees() throws Exception {
		mockMvc.perform(get("/api/fee"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].type").value("delivery"))
				.andExpect(jsonPath("$[0].value").value(5.0));
	}
}
