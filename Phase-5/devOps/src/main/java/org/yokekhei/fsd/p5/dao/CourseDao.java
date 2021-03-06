package org.yokekhei.fsd.p5.dao;

import java.util.List;

import org.yokekhei.fsd.p5.dto.Course;

public interface CourseDao {

	List<Course> getAllCourses() throws DevOpsDaoException;

	Course getCourse(Long id) throws DevOpsDaoException;

	Course save(Course course) throws DevOpsDaoException;

	void remove(Long id) throws DevOpsDaoException;

}
