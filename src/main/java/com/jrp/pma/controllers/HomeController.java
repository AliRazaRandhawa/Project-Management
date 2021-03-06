package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException
	{
		Map<String, Object> map = new HashMap<>();
		//we are querying the database for projects           
		List<Project> project = proRepo.findAll();
		model.addAttribute("projectsList", project);
		
		List<ChartData> projectData =proRepo.getProjectStatus();
		
		//Lets convert projectData object into a json structure for use of javascript
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonString =objectMapper.writeValueAsString(projectData);
		
		//[["NOTSTARTED",1],["INPROGRESS",2],["COMPLETED",3]]
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		
		
		//we are querying the database for employees
		//List<Employee> employee=empRepo.findAll(); 
		List<EmployeeProject> employee=empRepo.employeeProject(); 
		model.addAttribute("employeesListProjectsCnt", employee);
		return "main/home";
	}
	
}
