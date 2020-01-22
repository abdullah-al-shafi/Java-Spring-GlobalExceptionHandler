package com.spring5.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring5.practice.exceptions.ResourceAlreadyExistsException;
import com.spring5.practice.exceptions.ResourceNotFoundException;
import com.spring5.practice.model.Country;
import com.spring5.practice.model.Course;

@Service
public class CourseSercvice {

	private static List<Course> courcses = new ArrayList<Course>();
	
	
	public void addCourse(Course course) {
		checkCourseInList(course);
		course.setCourseId(courcses.size() + 1);
		courcses.add(course);
	}

	public void checkCourseInList(Course c) {
		if (courcses.stream().filter(course -> course.getCourseCode().equals(c.getCourseCode())).findAny()
				.isPresent()) {
			throw new ResourceAlreadyExistsException("Course already exists in list");
		}
	}
	
	public Course getCourseByCode(String courseCode) {


		return courcses.stream().filter(course -> course.getCourseCode().equals(courseCode)).findAny()
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with this code"));

	}

	public List<Course> getAll() {
		return courcses;
	}
}
