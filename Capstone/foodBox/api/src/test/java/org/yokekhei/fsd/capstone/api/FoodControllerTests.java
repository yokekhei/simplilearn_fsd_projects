package org.yokekhei.fsd.capstone.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = FoodBoxApplication.class)
public class FoodControllerTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test(priority = 0)
	public void testGetFoodById() throws Exception {
		mockMvc.perform(get("/api/food/{id}", 2))
				.andExpect(jsonPath("$.name").value("Spareribs"))
				.andExpect(jsonPath("$.categoryId").value(1))
				.andExpect(jsonPath("$.price").value(12.5))
				.andExpect(jsonPath("$.desc").value("Tender, juicy, tangy ribs cooked on the grill."))
				.andExpect(jsonPath("$.offerId").value(2))
				.andExpect(jsonPath("$.enabled").value(true));
	}

}
