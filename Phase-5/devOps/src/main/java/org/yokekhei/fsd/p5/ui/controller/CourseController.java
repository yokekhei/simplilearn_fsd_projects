package org.yokekhei.fsd.p5.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yokekhei.fsd.p5.dto.Course;
import org.yokekhei.fsd.p5.service.CourseService;
import org.yokekhei.fsd.p5.service.DevOpsServiceException;
import org.yokekhei.fsd.p5.ui.View;

@Controller
public class CourseController {

	private static final int COL_NUM = 3;

	@Autowired
	private CourseService service;

	@GetMapping("/admin/courses")
	public String courseList(Model model, HttpServletRequest request) throws DevOpsServiceException {
		List<Course> courseList = service.getAllCourses();
		request.getSession(false).setAttribute("courseList", courseList);

		return View.V_ADMIN_COURSE_LIST;
	}

	@GetMapping("/courses")
	public String courseCatalog(@RequestParam(required = false, name = "categoryId") Long categoryId,
			HttpServletRequest request) throws DevOpsServiceException {
		int rowNumber = 0;
		List<Course> courses = service.getAllCourses();

		List<List<Course>> courseList = new ArrayList<>();

		if (courses.size() > 0) {
			rowNumber = (courses.size() / COL_NUM) + (courses.size() % COL_NUM) - 1;

			int index = 0;
			int count = courses.size();

			for (int row = 0; row <= rowNumber; row++) {
				List<Course> rowCourses = new ArrayList<>();

				for (int col = 0; col < COL_NUM && count > 0; col++) {
					Course course = courses.get(index++);
					rowCourses.add(course);
					count--;
				}

				courseList.add(rowCourses);
			}
		}

		request.setAttribute("courseList", courseList);

		return View.V_USER_COURSE_CATALOG;
	}

	@GetMapping("/course")
	public String catalogProduct(@RequestParam(name = "id") Long id, HttpServletRequest request, Model model)
			throws DevOpsServiceException {
		Course course = service.getCourse(id);
		model.addAttribute("course", course);

		return View.V_USER_COURSE;
	}

	@GetMapping("/course/image")
	public void showImage(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws DevOpsServiceException {
		try {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");

			Resource resource = new ClassPathResource(View.DEFAULT_COURSE_IMAGE);
			byte[] picture = IOUtils.toByteArray(resource.getInputStream());

			response.getOutputStream().write(picture);
			response.getOutputStream().close();
		} catch (IOException e) {
			throw new DevOpsServiceException(e.getMessage());
		}
	}

	@ExceptionHandler(DevOpsServiceException.class)
	public String handlerException(DevOpsServiceException exception, HttpServletRequest request) {
		request.getSession(false).setAttribute("alert", exception.getMessage());

		return "redirect:/" + View.V_USER_COURSE_CATALOG;
	}

}
