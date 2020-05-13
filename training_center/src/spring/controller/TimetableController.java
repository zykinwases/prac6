package spring.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import database.Course;
import database.Lesson;
import database.StdLess;
import database.Student;
import database.Training_center;
import forms.LessonForm;

@Controller
public class TimetableController {
    @Autowired
    private Training_center tc;
    @Autowired
    private UserInfo user;
    
    private boolean checkAtt(Student st, Course c) throws SQLException {
    	List<StdLess> std = (List<StdLess>) tc.getStdLessDAO().getCoursesByStudent(st.getStudent_id());
    	Iterator<StdLess> iter = std.iterator();
    	while (iter.hasNext()) {
    		StdLess s = iter.next();
    		if (s.getCourse_id().getCourse_id() == c.getCourse_id()) return true;
    	}
    	return false;
    }
    
    @RequestMapping(value = "/timetable", method = RequestMethod.GET)
    public String getIndex(ModelMap map, @RequestParam(value="time", required=false) String time) {
        try {
        	map.addAttribute("user", user);
        	
        	List<Lesson> lessons = new ArrayList<Lesson>();
        	Iterator<Lesson> iter;
        	Date now = new Date();
        	long mils = now.getTime();
        	if (time == null) lessons = (List<Lesson>) tc.getLessonDAO().getAllLessons();
        	else if (time.equals("today")) {
        		lessons = (List<Lesson>) tc.getLessonDAO().getLessonByTime(new Timestamp(mils), new Timestamp(mils - (mils+3*3600000)%86400000 + 86400000));
        	} else if (time.equals("tomorrow")) {
        		lessons = (List<Lesson>) tc.getLessonDAO().getLessonByTime(new Timestamp(mils), new Timestamp(mils - (mils+3*3600000)%86400000 + 86400000*2));
        	} else if (time.equals("week")) {
        		lessons = (List<Lesson>) tc.getLessonDAO().getLessonByTime(new Timestamp(mils), new Timestamp(mils - (mils+3*3600000)%86400000 + 86400000*7));
        	} else {
        		lessons = (List<Lesson>) tc.getLessonDAO().getAllLessons();
        	}
        	iter = lessons.iterator();
        	if (user.getRole().equals("student")) {
        		while (iter.hasNext()) {
        			Lesson les = iter.next();
        			if (!checkAtt(tc.getStudentDAO().getStudentById(user.getId()), les.getCourse_id())) iter.remove();
        		}
        	} else if (user.getRole().equals("professor")) {
        		while(iter.hasNext()) {
        			Lesson les = iter.next();
        			if (!(les.getCourse_id().getProfessor_id().getProfessor_id() == user.getId())) iter.remove();
        		}
        	}
        	map.addAttribute("lessonList", lessons);
            return "timetable";
        } catch (Exception e) {
        	map.addAttribute("errorMessage", e.toString());
            return "error";
        }
    }
    
    @RequestMapping(value="/timetable/delete", method=RequestMethod.GET)
    public String delete(ModelMap map, @RequestParam(value="lesson") int id) {
    	if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
    	try {
    		Lesson lesson = tc.getLessonDAO().getLessonById((long) id);
    		tc.getLessonDAO().deleteLesson(lesson);
    		return "redirect:/timetable";
    	} catch (Exception e) {
    		map.addAttribute("errorMessage", e.toString());
    		return "error";
    	}
    }
    
    @RequestMapping(value="/timetable/add", method=RequestMethod.GET)
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
    		map.addAttribute("lessonForm", new LessonForm());
    		map.addAttribute("courseList", tc.getCourseDAO().getAllCourses());
    		return "timetable_add";
    	} catch (Exception e) {
    		map.addAttribute("errorMessage", e.toString());
    		return "error";
    	}
    }
    
    @RequestMapping(value="/timetable/add", method=RequestMethod.POST)
    public String addForm(ModelMap map, @ModelAttribute("lessonForm") LessonForm lessonForm) {
    	try {
    		Lesson lesson = new Lesson(tc.getCourseDAO().getCourseById(lessonForm.getCourse_id()), lessonForm.getTime());
    		tc.getLessonDAO().addLesson(lesson);
    		return "redirect:/timetable";
    	} catch (Exception e) {
    		map.addAttribute("errorMessage", e.toString());
    		return "error";
    	}
    }
}
