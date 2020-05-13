package spring.controller;

import database.Admin;
import database.Professor;
import database.Student;
import database.Training_center;
import forms.AuthForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private Training_center tc;
    @Autowired
    private UserInfo user;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(ModelMap map, @RequestParam(value="errorMessage", required=false) String errorMessage) {
        try {
        	map.addAttribute("errorMessage", errorMessage);
        	map.addAttribute("authForm", new AuthForm());
            return "auth";
        } catch (Exception e) {
        	map.addAttribute("errorMessage", e.toString());
            return "error";
        }
    }
    
    private long checkStudent(String login, String pswd_hash) {
    	try {
    		Student s = tc.getStudentDAO().getStudentByLogin(login);
    		if (s == null) return 0;
    		if (s.getPswd_hash().equals(pswd_hash)) {
    			user.getInstance("student", s.getStudent_id());
    			return s.getStudent_id();
    		}
    		else return 0;
    	} catch (Exception e) {
    		return 0;
    	}
    }
    
    private long checkProf(String login, String pswd_hash) {
    	try {
    		Professor p = tc.getProfessorDAO().getProfessorByLogin(login);
    		if (p == null) return 0;
    		if (p.getPswd_hash().equals(pswd_hash)) {
    			user.getInstance("professor", p.getProfessor_id());
    			return p.getProfessor_id();
    		}
    		else return 0;
    	} catch (Exception e) {
    		return 0;
    	}
    }
    
    private long checkAdmin(String login, String pswd_hash) {
    	try {
    		Admin a = tc.getAdminDAO().getAdminByLogin(login);
    		if (a == null) return 0;
    		if (a.getPswd_hash().equals(pswd_hash)) {
    			user.getInstance("admin", a.getAdmin_id());
    			return a.getAdmin_id();
    		}
    		else return 0;
    	} catch (Exception e) {
    		return 0;
    	}
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String auth(ModelMap map, @ModelAttribute("authForm") AuthForm authForm) {
    	try {
    		long id;
    		if (authForm.getRole().equals("Student")) {
    			id = checkStudent(authForm.getLogin(), authForm.getPswd_hash());
    			if (id != 0) return "redirect:student/" + id;
    		} else if (authForm.getRole().equals("Professor")) {
    			id = checkProf(authForm.getLogin(), authForm.getPswd_hash());
    			if (id != 0) return "redirect:professor/" + id;
    		} else if (authForm.getRole().equals("Administrator")) {
    			id = checkAdmin(authForm.getLogin(), authForm.getPswd_hash());
    			if (id != 0) return "redirect:admin/" + id;
    		}
    		map.addAttribute("errorMessage", "Wrong username or password");
    		return "redirect:/";
    	} catch (Exception e) {
    		map.addAttribute("errorMessage", e.toString());
    		return "error";
    	}
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap map) {
    	user.delete();
    	map.addAttribute("errorMessage", "Logged out");
    	return "redirect:/";
    }
}
