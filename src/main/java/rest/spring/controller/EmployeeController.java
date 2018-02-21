package rest.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.spring.entity.Employee;
import rest.spring.entity.Manager;
import rest.spring.service.EmployeeServiceImpl;
import rest.spring.service.ManagerServiceImpl;

@RestController
@RequestMapping("/managers/{managerId}")
public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	@Autowired
	ManagerServiceImpl managerService;
	
	/**
	 * Get list of all employee
	 * @return
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> employeeList(@PathVariable("managerId") Long managerId) {
		
		Manager manager = null;
		List <Employee> employees = null;
		
		try {
			manager = managerService.findById(managerId) ;
			
			if(manager == null) {
				return new ResponseEntity("Manager with manager id " + managerId + " does not exist",HttpStatus.NO_CONTENT);
			}
			else if(manager != null) {
				employees = employeeService.findAllByManager(manager);
				
				if(employees.isEmpty()) {
					return new ResponseEntity("There have no employees with manager id " + managerId ,HttpStatus.NO_CONTENT);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	/**
	 * Get employee by id
	 * @param employeeId
	 * @return
	 */
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="managerId") Long managerId, @PathVariable(value = "id") Long employeeId) {
	    Employee employee = employeeService.findById(employeeId);
	    if(employee == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(employee);
	}
	
	/**
	 * Save a new employee
	 * @param employee
	 * @return
	 */
	@PostMapping("employees/create")
	public ResponseEntity<Employee> createEmployee(@PathVariable("managerId") Long managerId, @Valid @RequestBody Employee employee) {
	    
		Manager manager = null;
		
		manager = managerService.findById(managerId);
		
		if(manager == null) {
			return new ResponseEntity("Manager with manager id " + managerId + "is not exist", HttpStatus.NO_CONTENT);
		}
		
		employee.setManager(manager);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		manager.setEmployees(employees);
		/*
		 * To do check manager have at least one employee ohterwise manager.getEmployees().add(employee) will return 
		 * null pointer exception
		 */
		//manager.getEmployees().add(employee);
		
		Manager managerTemp = null;
		managerTemp = managerService.save(manager);
		if(managerTemp == null) {
			return new ResponseEntity("Employee cant be created",HttpStatus.FAILED_DEPENDENCY);
		}
		return new ResponseEntity(employee,HttpStatus.CREATED);
	}
	
	/**
	 * Update an employee by id
	 * @param employeeId
	 * @param employeeDetails
	 * @return
	 */
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("managerId") Long managerId, @PathVariable(value = "id") Long employeeId, 
	                                       @Valid @RequestBody Employee employeeDetails) {
		Manager manager = null;
		
		manager = managerService.findById(managerId);
		
		if(manager == null) {
			return new ResponseEntity("Manager with manager id " + managerId + "is not exist", HttpStatus.NO_CONTENT);
		}
		Employee employee = null;
		employee = employeeService.findById(employeeId);
		if(employee == null) {
	        return new ResponseEntity("Employee with employee id " + employeeId + "is not exist",HttpStatus.NO_CONTENT);
	    }
		
	    employee.setName(employeeDetails.getName());
	    employee.setEmail(employeeDetails.getEmail());
	    employee.setDesignation(employeeDetails.getDesignation());
	    employee.setSalary(employeeDetails.getSalary());
	    employee.setManager(manager);

	    Employee updatedEmployee = employeeService.update(employee);
	    return new ResponseEntity(updatedEmployee,HttpStatus.OK);
	}
	
	/**
	 * Delete an employee by id
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("managerId") Long managerId, @PathVariable(value = "id") Long employeeId) {
	    
		Manager manager = null;
		
		manager = managerService.findById(managerId);
		
		if(manager == null) {
			return new ResponseEntity("Manager with manager id " + managerId + "is not exist", HttpStatus.NO_CONTENT);
		}
		
		Employee employee = employeeService.findById(employeeId);
	    if(employee == null) {
	        return ResponseEntity.notFound().build();
	    }

	    employeeService.delete(employee);
	    return ResponseEntity.ok().build();
	}
	
}
