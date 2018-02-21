package rest.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rest.spring.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByName(String name);
}