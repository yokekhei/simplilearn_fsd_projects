package org.yokekhei.fsd.p5.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.yokekhei.fsd.p5.ui.View;

@Controller
public class CourseController {

	@GetMapping("/courses")
	public String courseCatalog() {
		return View.V_USER_COURSE_CATALOG;
	}

	@GetMapping("/admin/courses")
	public String courseList() {
		return View.V_ADMIN_COURSE_LIST;
	}

}
