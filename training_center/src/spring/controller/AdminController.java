package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import database.Admin;
import database.Training_center;
import forms.AdminForm;

@Controller
public class AdminController {
	@Autowired
	private Training_center tc;
	@Autowired
	private UserInfo user;
	
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
	public String getStudent(ModelMap map, @PathVariable("id") int id, @RequestParam(value="errorMessage", required=false) String errorMessage) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		try {
			map.addAttribute("errorMessage", errorMessage);
			map.addAttribute("user", user);
			map.addAttribute("admin", tc.getAdminDAO().getAdminById((long) id));
			return "admin";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/admin/{id}/delete", method = RequestMethod.GET)
	public String delAdmin(ModelMap map, @PathVariable("id") int id) {
		if ((user == null) || (user.getId() == 0)) {
			map.addAttribute("errorMessage", "Login required");
			return "redirect:/";
		}
		if (user.getRole() != "admin") {
			map.addAttribute("errorMessage", "You are not allowed to do this");
			return "redirect:/" + user.getRole() + "/" + user.getId();
		}
		try {
			tc.getAdminDAO().deleteAdmin(tc.getAdminDAO().getAdminById((long) id));
			return "redirect:/admins";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/admin/{id}/edit", method = RequestMethod.GET)
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
			map.addAttribute("admin", tc.getAdminDAO().getAdminById((long) id));
			map.addAttribute("adminForm", new AdminForm());
			return "admin_update";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/admin/{id}/edit", method = RequestMethod.POST)
	public String updateForm(ModelMap map, @PathVariable("id") int id, @ModelAttribute("adminForm") AdminForm adminForm) {
		try {
			Admin admin = tc.getAdminDAO().getAdminById(adminForm.getAdmin_id());
			admin.setFirst_name(adminForm.getFirst_name());
			admin.setLast_name(adminForm.getLast_name());
			tc.getAdminDAO().updateAdmin(admin);
			return "redirect:/admin/" + id;
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public String getAll(ModelMap map) {
		try {
			map.addAttribute("user", user);
			map.addAttribute("adminList", tc.getAdminDAO().getAllAdmins());
			return "admins";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/admins/add", method = RequestMethod.GET)
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
			map.addAttribute("adminForm", new AdminForm());
			return "admin_add";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping(value = "/admins/add", method = RequestMethod.POST)
	public String addForm(ModelMap map, @ModelAttribute("adminForm") AdminForm adminForm) {
		try {
			Admin admin = new Admin(adminForm.getLogin(), adminForm.getPswd_hash(), adminForm.getLast_name(), adminForm.getFirst_name());
			tc.getAdminDAO().addAdmin(admin);
			return "redirect:/admins";
		} catch (Exception e) {
			return "error";
		}
	}
}
