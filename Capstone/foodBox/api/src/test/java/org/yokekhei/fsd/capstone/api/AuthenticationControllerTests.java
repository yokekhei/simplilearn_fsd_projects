package org.yokekhei.fsd.capstone.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yokekhei.fsd.capstone.api.dto.Address;
import org.yokekhei.fsd.capstone.api.dto.User;

@SpringBootTest(classes = FoodBoxApplication.class)
public class AuthenticationControllerTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	private User user;
	private Address address;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		address = new Address("123 Sunshine Apartment", "St NW", "Kuala Lumpur", "57000");
		user = new User("johndoe@gmail.com", "Pa$sw0rd", "johndoe", "+60-127813456", "U", address, true);
	}

	@Test(priority = 0)
	public void testLogin() throws Exception {
		mockMvc.perform(post("/api/login").content(TestUtils.asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.email").value("johndoe@gmail.com"))
				.andExpect(jsonPath("$.password").value("Pa$sw0rd"))
				.andExpect(jsonPath("$.username").value("johndoe"))
				.andExpect(jsonPath("$.phone").value("+60-127813456"))
				.andExpect(jsonPath("$.role").value("U"))
				.andExpect(jsonPath("$.enabled").value(true));
	}
	
	@Test(priority = 1)
	public void testRegister() throws Exception {
		user.setEmail("ramesh@gmail.com");
		user.setUsername("ramesh");
		mockMvc.perform(post("/api/register").content(TestUtils.asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.email").value("ramesh@gmail.com"))
				.andExpect(jsonPath("$.password").value("Pa$sw0rd"))
				.andExpect(jsonPath("$.username").value("ramesh"))
				.andExpect(jsonPath("$.phone").value("+60-127813456"))
				.andExpect(jsonPath("$.role").value("U"))
				.andExpect(jsonPath("$.enabled").value(true));
	}

}
