package spring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.Course;
import database.StdLess;
import database.StdLessHyst;
import database.Student;
import database.Training_center;
import forms.CourseForm;

@Controller
public class CourseController {
	@Autowired
	private Training_center tc;
	@Autowired
	private UserInfo user;
	
	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public String getStudent(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		try {
			map.addAttribute("user", user);
			Course course = tc.getCourseDAO().getCourseById((long) id);
			map.addAttribute("course", course);
			map.addAttribute("lessonList", tc.getLessonDAO().getLessonsByCourse(course));
			
			List<StdLess> std = (List<StdLess>) tc.getStdLessDAO().getStudentsByCourse((long) id);
			List<Student> students = new ArrayList<Student>();
			Iterator<StdLess> iter1 = std.iterator();
			while (iter1.hasNext()) {
				StdLess s = iter1.next();
				students.add(s.getStudent_id());
			}
			map.addAttribute("studentList", students);
			
			List<StdLessHyst> stdOld = (List<StdLessHyst>) tc.getStdLessHystDAO().getStudentsByCourse((long) id);		
			List<Student> oldStudents = new ArrayList<Student>();
			Iterator<StdLessHyst> iter2 = stdOld.iterator();
			while (iter2.hasNext()) {
				StdLessHyst s = iter2.next();
				oldStudents.add(s.getStudent_id());
			}
			map.addAttribute("oldList", oldStudents);
			return "course";
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value = "/course/{id}/delete", method = RequestMethod.GET)
	public String delCourse(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			tc.getCourseDAO().deleteCourse(tc.getCourseDAO().getCourseById((long) id));
			return "redirect:/courses";
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value = "/course/{id}/edit", method = RequestMethod.GET)
	public String update(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			map.addAttribute("user", user);
			map.addAttribute("course", tc.getCourseDAO().getCourseById((long) id));
			map.addAttribute("professorList", tc.getProfessorDAO().getAllProfessors());
			map.addAttribute("courseForm", new CourseForm());
			return "course_update";
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value = "/course/{id}/edit", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @PathVariable("id") int id, @ModelAttribute("courseForm") CourseForm courseForm) {
		try {
			Course course = tc.getCourseDAO().getCourseById(courseForm.getCourse_id());
			course.setName(courseForm.getName());
			course.setDuration(courseForm.getDuration());
			course.setIntense(course.getIntense());
			course.setProfessor_id(tc.getProfessorDAO().getProfessorById(courseForm.getProf_id()));
			tc.getCourseDAO().updateCourse(course);
			return "redirect:/course/" + id;
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String getAll(ModelMap map) {
		try {
			map.addAttribute("user", user);
			map.addAttribute("courseList", tc.getCourseDAO().getAllCourses());
			return "courses";
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value = "/courses/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			map.addAttribute("user", user);
			map.addAttribute("professorList", tc.getProfessorDAO().getAllProfessors());
			map.addAttribute("courseForm", new CourseForm());
			return "course_add";
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value = "/courses/add", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @ModelAttribute("courseForm") CourseForm courseForm) {
		try {
			Course course = new Course(courseForm.getName(), courseForm.getDuration(), courseForm.getIntense(),
							tc.getProfessorDAO().getProfessorById(courseForm.getProf_id()));
			tc.getCourseDAO().addCourse(course);
			return "redirect:/courses";
		} catch (Exception e) {
			map.addAttribute("errorMessage", e.toString());
			return "error";
		}
	}
}
