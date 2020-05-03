package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.Company;
import database.Training_center;
import forms.CompanyForm;

@Controller
public class CompanyController {
	@Autowired
	private Training_center tc;
	@Autowired
	private UserInfo user;
	
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	public String getStudent(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		try {
			map.addAttribute("user", user);
			Company c = tc.getCompanyDAO().getCompanyById((long) id);
			map.addAttribute("company", c);	
			map.addAttribute("professorList", tc.getProfessorDAO().getProfessorsByCompany(c));
			return "company";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/company/{id}/delete", method = RequestMethod.GET)
	public String delCompany(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/company/" + id;
		}
		try {
			tc.getCompanyDAO().deleteCompany(tc.getCompanyDAO().getCompanyById((long) id));
			return "redirect:/companies";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/company/{id}/edit", method = RequestMethod.GET)
	public String update(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/company/" + id;
		}
		try {
			map.addAttribute("user", user);
			map.addAttribute("company", tc.getCompanyDAO().getCompanyById((long) id));
			map.addAttribute("companyUpdate", new CompanyForm());
			return "company_update";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/company/{id}/edit", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @PathVariable("id") int id, @ModelAttribute("companyUpdate") CompanyForm companyUpdate) {
		try {
			Company company = tc.getCompanyDAO().getCompanyById(companyUpdate.getCompany_id());
			company.setName(companyUpdate.getName());
			company.setAddress(companyUpdate.getAddress());
			tc.getCompanyDAO().updateCompany(company);
			return "redirect:/company/" + id;
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public String getAll(ModelMap map) {
		try {
			map.addAttribute("user", user);
			map.addAttribute("companyList", tc.getCompanyDAO().getAllCompanies());
			return "companies";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/companies/add", method = RequestMethod.GET)
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
			map.addAttribute("companyForm", new CompanyForm());
			return "company_add";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/companies/add", method = RequestMethod.POST)
	public String addForm(ModelMap map, @ModelAttribute("companyForm") CompanyForm companyForm) {
		try {
			Company company = new Company(companyForm.getName(), companyForm.getAddress());
			tc.getCompanyDAO().addCompany(company);
			return "redirect:/companies";
		} catch (Exception e) {
			return "error";
		}
	}
}
