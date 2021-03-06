package org.yokekhei.fsd.p5.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.yokekhei.fsd.p5.dto.Course;
import org.yokekhei.fsd.p5.service.CourseService;
import org.yokekhei.fsd.p5.service.DevOpsServiceException;
import org.yokekhei.fsd.p5.ui.View;

@Controller
public class CourseController {

	@Autowired
	private CourseService service;

	@GetMapping("/courses")
	public String courseCatalog() {
		return View.V_USER_COURSE_CATALOG;
	}

	@GetMapping("/admin/courses")
	public String courseList(Model model, HttpServletRequest request) throws DevOpsServiceException {
		List<Course> courseList = service.getAllCourses();
		request.getSession(false).setAttribute("courseList", courseList);

		return View.V_ADMIN_COURSE_LIST;
	}

}
