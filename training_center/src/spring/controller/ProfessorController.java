package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.Professor;
import database.Training_center;
import forms.ProfessorForm;

@Controller
public class ProfessorController {
	@Autowired
	private Training_center tc;
	@Autowired
	private UserInfo user;
	
	@RequestMapping(value = "/professor/{id}", method = RequestMethod.GET)
	public String getStudent(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		try {
			map.addAttribute("user", user);
			Professor prof = tc.getProfessorDAO().getProfessorById((long) id);
			map.addAttribute("professor", prof);
			map.addAttribute("courseList", tc.getCourseDAO().getCoursesByProf(prof));
			return "professor";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/professor/{id}/delete", method = RequestMethod.GET)
	public String delProf(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/professor/" + id;
		}
		try {
			tc.getProfessorDAO().deleteProfessor(tc.getProfessorDAO().getProfessorById((long) id));
			return "redirect:/professors";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/professor/{id}/edit", method = RequestMethod.GET)
	public String update(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/professor/" + id;
		}
		try {
			map.addAttribute("user", user);
			map.addAttribute("professor", tc.getProfessorDAO().getProfessorById((long) id));
			map.addAttribute("companyList", tc.getCompanyDAO().getAllCompanies());
			map.addAttribute("professorForm", new ProfessorForm());
			return "professor_update";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/professor/{id}/edit", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @PathVariable("id") int id, @ModelAttribute("professorForm") ProfessorForm professorForm) {
		try {
			Professor prof = tc.getProfessorDAO().getProfessorById(professorForm.getProfessor_id());
			prof.setFirst_name(professorForm.getFirst_name());
			prof.setLast_name(professorForm.getLast_name());
			prof.setCompany_id(tc.getCompanyDAO().getCompanyById(professorForm.getCompany_id()));
			tc.getProfessorDAO().updateProfessor(prof);
			return "redirect:/professor/" + id;
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/professors", method = RequestMethod.GET)
	public String getAll(ModelMap map) {
		try {
			map.addAttribute("user", user);
			map.addAttribute("profList", tc.getProfessorDAO().getAllProfessors());
			return "professors";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/professors/add", method = RequestMethod.GET)
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
			map.addAttribute("companyList", tc.getCompanyDAO().getAllCompanies());
			map.addAttribute("professorForm", new ProfessorForm());
			return "professor_add";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/professors/add", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @ModelAttribute("professorForm") ProfessorForm professorForm) {
		try {
			Professor professor = new Professor(professorForm.getLogin(), professorForm.getPswd_hash(), professorForm.getLast_name(),
							professorForm.getFirst_name(), tc.getCompanyDAO().getCompanyById(professorForm.getCompany_id()));
			tc.getProfessorDAO().addProfessor(professor);
			return "redirect:/professors";
		} catch (Exception e) {
			return "error";
		}
	}
}
