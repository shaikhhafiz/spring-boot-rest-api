package rest.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rest.spring.entity.Employee;
import rest.spring.entity.Manager;
import rest.spring.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	@Transactional
	public Manager findById(long id) {
		return managerRepository.findById(id);
	}

	@Override
	@Transactional
	public List<Manager> findAll() {
		return managerRepository.findAll();
	}

	@Override
	@Transactional
	public Manager save(Manager manager) {
		return managerRepository.save(manager);
	}

	@Override
	@Transactional
	public void delete(Manager manager) {
		managerRepository.delete(manager);
	}

	@Override
	@Transactional
	public Manager update(Manager manager) {
	    return managerRepository.save(manager);
	}

}
