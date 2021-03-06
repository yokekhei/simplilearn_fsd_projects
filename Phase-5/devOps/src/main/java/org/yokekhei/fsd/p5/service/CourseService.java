package org.yokekhei.fsd.p5.service;

import java.util.List;

import org.yokekhei.fsd.p5.dto.Course;

public interface CourseService {

	List<Course> getAllCourses() throws DevOpsServiceException;

	void addCourse(Course course) throws DevOpsServiceException;

	void updateCourse(Course course) throws DevOpsServiceException;

	void deleteCourse(Long id) throws DevOpsServiceException;

}
