package rest.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.spring.entity.Employee;
import rest.spring.entity.Manager;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE LOWER(e.id) = LOWER(:id)")
    public Employee findById(@Param("id") Long id);
	
	@Query("SELECT e FROM Employee e WHERE LOWER(e.manager) = LOWER(:manager)")
	public List<Employee> findByManager(@Param("manager") Manager manager);
}
