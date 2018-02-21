package rest.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rest.spring.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	@Query("SELECT m FROM Manager m WHERE LOWER(m.id) = LOWER(:id)")
	public Manager findById(@Param("id") Long id);
	
}
