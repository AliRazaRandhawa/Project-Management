package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayPrjects(Model model) {
		List<Employee> employee = empRepo.findAll();
		model.addAttribute("employees", employee);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeesForm(Model model) {
		model.addAttribute("employees", new Employee());
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployeesForm(Employee employee) {

		empRepo.save(employee);
		return "redirect:/employees/new";
	}

}