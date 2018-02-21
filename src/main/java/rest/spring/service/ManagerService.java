package rest.spring.service;

import java.util.List;

import rest.spring.entity.Manager;

public interface ManagerService {
	public Manager findById(long id);
	public List<Manager> findAll();
	public Manager save(Manager manager);
	public Manager update(Manager manager);
	public void delete(Manager manager);
}
