package org.yokekhei.fsd.p5.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yokekhei.fsd.p5.dao.CourseDao;
import org.yokekhei.fsd.p5.dto.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	CourseDao courseDao;

	@Override
	public List<Course> getAllCourses() throws DevOpsServiceException {
		List<Course> courses = null;

		try {
			courses = courseDao.getAllCourses();
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}

		return courses;
	}

	@Override
	public Course getCourse(Long id) throws DevOpsServiceException {
		Course course = null;

		try {
			course = courseDao.getCourse(id);
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}

		return course;
	}

	@Override
	public void addCourse(Course course) throws DevOpsServiceException {
		try {
			courseDao.save(course);
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}
	}

	@Override
	public void updateCourse(Course course) throws DevOpsServiceException {
		try {
			courseDao.save(course);
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteCourse(Long id) throws DevOpsServiceException {
		try {
			courseDao.remove(id);
		} catch (Exception e) {
			throw new DevOpsServiceException(e.getMessage());
		}
	}

}
