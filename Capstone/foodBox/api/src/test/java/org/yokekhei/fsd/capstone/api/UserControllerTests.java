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
public class UserControllerTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test(priority = 0)
	public void testGetUserById() throws Exception {
		mockMvc.perform(get("/api/user/{email}", "johndoe@gmail.com"))
				.andExpect(jsonPath("$.email").value("johndoe@gmail.com"))
				.andExpect(jsonPath("$.password").value("Pa$sw0rd"))
				.andExpect(jsonPath("$.username").value("johndoe"))
				.andExpect(jsonPath("$.phone").value("+60-127813456"))
				.andExpect(jsonPath("$.role").value("U"))
				.andExpect(jsonPath("$.enabled").value(true));
	}

	@Test(priority = 1)
	public void testGetUsers() throws Exception {
		mockMvc.perform(get("/api/user"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].email").value("johndoe@gmail.com"))
				.andExpect(jsonPath("$[1].password").value("Pa$sw0rd"))
				.andExpect(jsonPath("$[1].username").value("johndoe"))
				.andExpect(jsonPath("$[1].phone").value("+60-127813456"))
				.andExpect(jsonPath("$[1].role").value("U"))
				.andExpect(jsonPath("$[1].enabled").value(true));
	}

}
