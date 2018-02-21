package rest.spring.service;

import java.util.List;

import rest.spring.entity.Employee;
import rest.spring.entity.Manager;

public interface EmployeeService {
	public Employee findById(long id);
	public List<Employee> findAll();
	public Employee save(Employee employee);
	public Employee update(Employee employee);
	public void delete(Employee employee);
	public List<Employee> findAllByManager(Manager manager);
}
