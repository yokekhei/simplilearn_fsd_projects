package org.yokekhei.fsd.capstone.api;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.yokekhei.fsd.capstone.api.dto.Category;

@SpringBootTest(classes = FoodBoxApplication.class)
public class CategoryControllerTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	private Category category;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		category = new Category(1L, "Chinese", true);
	}

	@Test(priority = 0)
	public void testGetCategory() throws Exception {
		mockMvc.perform(get("/api/category/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Chinese"))
				.andExpect(jsonPath("$.enabled").value(true));
	}

	@Test(priority = 1)
	public void testGetCategories() throws Exception {
		mockMvc.perform(get("/api/category"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Chinese"))
				.andExpect(jsonPath("$[0].enabled").value(true));
	}
	
	@Test(priority = 2)
	public void testDisableCategory() throws Exception {
		category.setEnabled(false);
		mockMvc.perform(put("/api/category/{id}/enabled", category.getId())
				.content(TestUtils.asJsonString(category))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(category.getId()))
				.andExpect(jsonPath("$.name").value("Chinese"))
				.andExpect(jsonPath("$.enabled").value(false));
	}
	
	@Test(priority = 3)
	public void testUpdateCategory() throws Exception {
		category.setEnabled(true);
		mockMvc.perform(put("/api/category")
				.content(TestUtils.asJsonString(category))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(category.getId()))
				.andExpect(jsonPath("$.name").value("Chinese"))
				.andExpect(jsonPath("$.enabled").value(true));
	}
	
//	@Test(priority = 4)
//	public void testDeleteCategory() throws Exception {
//		category.setEnabled(false);
//		mockMvc.perform(delete("/api/category/{id}", category.getId())
//				.content(TestUtils.asJsonString(category))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json"))
//				.andExpect(jsonPath("$.id").value(category.getId()))
//				.andExpect(jsonPath("$.name").value("Indian"))
//				.andExpect(jsonPath("$.enabled").value(false));
//	}
//
//	@Test(priority = 5)
//	public void testCreateCategory() throws Exception {
//		mockMvc.perform(post("/api/category")
//				.content(TestUtils.asJsonString(category))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json"))
//				.andExpect(jsonPath("$.name").value("Chinese"))
//				.andExpect(jsonPath("$.enabled").value(true));
//	}

}
