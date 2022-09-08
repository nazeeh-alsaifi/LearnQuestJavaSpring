package com.student.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.student.core.College;
import com.student.service.StudentService;

@Controller
@RequestMapping("/school")
class SchoolController {
	
	@Inject
	private StudentService studentService;
	
	@GetMapping
	public ModelAndView getAllSchools() {
		Collection<College> colleges = studentService.getAllStudents().stream().map(p-> p.getCollege()).distinct().collect(Collectors.toList());
		return new ModelAndView("home", "colleges", colleges);
	}

}
