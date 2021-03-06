package org.yokekhei.fsd.p5.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.yokekhei.fsd.p5.dto.Course;
import org.yokekhei.fsd.p5.mapper.CourseMapper;
import org.yokekhei.fsd.p5.repository.CourseRepository;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Resource
	private CourseRepository repository;

	@Resource
	private CourseMapper mapper;

	@Override
	public List<Course> getAllCourses() throws DevOpsDaoException {
		List<Course> courses = null;

		try {
			courses = repository.findAll().stream()
					.map(entity -> mapper.toDto(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return courses;
	}

	@Override
	public Course getCourse(Long id) throws DevOpsDaoException {
		Course course = null;

		try {
			course = repository.findById(id)
					.map(entity -> mapper.toDto(entity))
					.orElse(null);
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return course;
	}

	@Override
	public Course save(Course course) throws DevOpsDaoException {
		Course savedCourse = null;

		try {
			savedCourse = mapper.toDto(repository.save(mapper.toEntity(course)));
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}

		return savedCourse;
	}

	@Override
	public void remove(Long id) throws DevOpsDaoException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new DevOpsDaoException(e.getMessage());
		}
	}

}
