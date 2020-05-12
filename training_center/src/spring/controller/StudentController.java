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
import org.springframework.web.bind.annotation.RequestParam;

import database.Course;
import database.StdLess;
import database.StdLessHyst;
import database.Student;
import database.Training_center;
import forms.StudentForm;

@Controller
public class StudentController {
	@Autowired
	private Training_center tc;
	@Autowired
	private UserInfo user;
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public String getStudent(ModelMap map, @PathVariable("id") int id, @RequestParam(value="errorMessage", required=false) String errorMessage) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		try {
			map.addAttribute("errorMessage", errorMessage);
			map.addAttribute("user", user);
			map.addAttribute("student", tc.getStudentDAO().getStudentById((long) id));
			
			List<StdLess> std = (List<StdLess>) tc.getStdLessDAO().getCoursesByStudent((long) id);
			List<Course> courses = new ArrayList<Course>();
			Iterator<StdLess> iter1 = std.iterator();
			while (iter1.hasNext()) {
				StdLess s = iter1.next();
				courses.add(s.getCourse_id());
			}
			map.addAttribute("coursesList", courses);
			
			List<StdLessHyst> stdOld = (List<StdLessHyst>) tc.getStdLessHystDAO().getCoursesByStudent((long) id);		
			List<Course> oldCourses = new ArrayList<Course>();
			Iterator<StdLessHyst> iter2 = stdOld.iterator();
			while (iter2.hasNext()) {
				StdLessHyst s = iter2.next();
				oldCourses.add(s.getCourse_id());
			}
			map.addAttribute("oldList", oldCourses);
			
			List<Course> rest = (List<Course>) tc.getCourseDAO().getAllCourses();
			Iterator<Course> iterc = rest.iterator();
			while(iterc.hasNext()) {
				Course c = iterc.next();
				Iterator<Course> iterc1 = courses.iterator();
				while (iterc1.hasNext()) {
					Course c1 = iterc1.next();
					if (c1.getCourse_id() == c.getCourse_id()) {
						iterc.remove();
						break;
					}
				}
			}
			map.addAttribute("allList", rest);
			return "student";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/student/{id}/delete", method = RequestMethod.GET)
	public String delStudent(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			tc.getStudentDAO().deleteStudent(tc.getStudentDAO().getStudentById((long) id));
			return "redirect:/students";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/student/{id}/edit", method = RequestMethod.GET)
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
			map.addAttribute("student", tc.getStudentDAO().getStudentById((long) id));
			map.addAttribute("studentForm", new StudentForm());
			return "student_update";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/student/{id}/edit", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @PathVariable("id") int id, @ModelAttribute("studentForm") StudentForm studentForm) {
		try {
			Student student = tc.getStudentDAO().getStudentById(studentForm.getStudent_id());
			student.setFirst_name(studentForm.getFirst_name());
			student.setLast_name(studentForm.getLast_name());
			tc.getStudentDAO().updateStudent(student);
			return "redirect:/student/" + id;
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/student/{id}/sign", method = RequestMethod.GET)
	public String signUp(ModelMap map, @PathVariable("id") int id, @RequestParam(value="course", required=true) int course) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			StdLess std = new StdLess(tc.getStudentDAO().getStudentById((long) id), tc.getCourseDAO().getCourseById((long) course));
			tc.getStdLessDAO().addStdLess(std);
			return "redirect:/student/" + id;
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/student/{id}/drop", method = RequestMethod.GET)
	public String drop(ModelMap map, @PathVariable("id") int id, @RequestParam(value="course", required=true) int course) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			StdLess std = new StdLess(tc.getStudentDAO().getStudentById((long) id), tc.getCourseDAO().getCourseById((long) course));
			tc.getStdLessDAO().deleteStdLess(std);
			return "redirect:/student/" + id;
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String getAll(ModelMap map) {
		try {
			map.addAttribute("user", user);
			map.addAttribute("studentList", tc.getStudentDAO().getAllStudents());
			return "students";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/students/add", method = RequestMethod.GET)
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
			map.addAttribute("studentForm", new StudentForm());
			return "student_add";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/students/add", method = RequestMethod.POST)
	public String addForm(ModelMap map, @ModelAttribute("studentForm") StudentForm studentForm) {
		try {
			Student student = new Student(studentForm.getLogin(), studentForm.getPswd_hash(), studentForm.getLast_name(), 
					studentForm.getFirst_name(), studentForm.getMobile());
			tc.getStudentDAO().addStudent(student);
			return "redirect:/stidents";
		} catch (Exception e) {
			return "error";
		}
	}
}
