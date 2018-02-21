package rest.spring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.spring.entity.Employee;
import rest.spring.entity.Manager;
import rest.spring.entity.User;
import rest.spring.repository.EmployeeRepository;
import rest.spring.repository.ManagerRepository;
import rest.spring.repository.UserRepository;


@Service
public class Bootstrap implements InitializingBean {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private final Logger log = LoggerFactory.getLogger(Bootstrap.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	/**
	 * initializing
	 */
	private void init() {
		initUser();
		initManagerEmployee();
		//initManager();
		//initEmployee();
	}
	
	/**
	 * Initializing user record in database
	 */
	private void initUser() {
		/**
		 * Check users if exists then return
		 */
		long countUser = userRepository.count();
		if(countUser >0 ) {
			log.info(countUser + "Users already exists");
			return;
		}
		
		
		List<User> users = new ArrayList<User>();
		
		users.add(createUser("hafiz","12345"));
		users.add(createUser("admin","12345"));
		
		userRepository.save(users);
		
		log.info("Some users has been created");
	}
	
	/**
	 * Create a user object setting value and return the object
	 * @param name
	 * @param password
	 * @return
	 */
	private User createUser(String name, String password){
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		return user;
	}
	
	/**
	 * Create employee with manager
	 */
	
	private void initManagerEmployee() {
		
		//employeeRepository.deleteAllInBatch();
		//managerRepository.deleteAllInBatch();
		
		long countEmployee = employeeRepository.count();
		long countManager = managerRepository.count();
		
		if(countManager > 0 || countEmployee > 0) {
			log.info(countManager + " managers and " + countEmployee+ " employee already exists");
			return;
		}
		
		Manager manager = this.buildManager("Umme Habiba","habiba@gmail.com","development","50000");
		
		Employee employee1 = this.buildEmployee("Hafiz ahmed", "hafiz@gmail.com", "Developer", "10000", manager);
		Employee employee2 = this.buildEmployee("Shaikh hafiz", "shaikh@gmail.com", "Developer", "10000", manager);
		
		employee1.setManager(manager);
		employee2.setManager(manager);
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee2);
		
		manager.setEmployees(employees);
		
		managerRepository.save(manager);
	}
	
	/**
	 * Initializing Manager record in database
	 */
	private void initManager() {
		long countManager = managerRepository.count();
		if(countManager > 0) {
			log.info(countManager + "managers already exists");
			return;
		}
		
		List<Manager> managers = new ArrayList<Manager>();
		
		managers.add(buildManager("hafiz","hafiz@gmail.com","development","15000"));
		
		managerRepository.save(managers);
		
		log.info("Some manager has been created");
	}
	
	
	/**
	 * Creating a manager object, setting value through setter methods and return the object
	 * @param name
	 * @param email
	 * @param department
	 * @param salary
	 * @return
	 */
	private Manager buildManager(String name, String email, String department, String salary) {
		Manager manager  = new Manager();
		
		manager.setName(name);
		manager.setEmail(email);
		manager.setDepartment(department);
		manager.setSalary(salary);
		
		return manager;
	}
	
	/**
	 * Initializing employee record in database
	 */
	private void initEmployee() {
		long countEmployee = employeeRepository.count();
		
		if(countEmployee > 0) {
			log.info(countEmployee + "Employee already exists");
			return;
		}
		List<Employee> employee = new ArrayList<Employee>();
		Manager manager = null;
		employee.add(buildEmployee("nobody","nobody@gmail.com","none","0",manager));
		
		employeeRepository.save(employee);
		
		log.info("Some employee has been created");
	}
	
	/**
	 * Creating a employee object setting it's value thorough setter method and return the object
	 * @param name
	 * @param email
	 * @param designation
	 * @param salary
	 * @return
	 */
	private Employee buildEmployee(String name, String email, String designation, String salary, Manager manager) {
		Employee employee = new Employee();
		
		employee.setName(name);
		employee.setEmail(email);
		employee.setDesignation(designation);
		employee.setSalary(salary);
		employee.setManager(manager);
		return employee;
	}
}
