package rest.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.spring.entity.Manager;
import rest.spring.service.ManagerServiceImpl;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	private static final Logger log = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	ManagerServiceImpl managerService;
	
	/**
	 * Get list of all Managers
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<List<Manager>> ManagerList() {
		List <Manager> managers = null;
		try {
			managers = managerService.findAll();
			if(managers.isEmpty()) {
				return new ResponseEntity("Manager list is empty",HttpStatus.OK);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return new ResponseEntity<List<Manager>>(managers, HttpStatus.OK);
	}
	
	/**
	 * Get Manager by id
	 * @param managerId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Manager> getManagerById(@PathVariable(value = "id") Long managerId) {
	    Manager manager = managerService.findById(managerId);
	    if(manager == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(manager);
	}
	
	/**
	 * Save a new Manager
	 * @param manager
	 * @return
	 */
	@PostMapping(value="/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Manager createManager(@Valid @RequestBody Manager manager) {
		return managerService.save(manager);
	}
	
	/**
	 * Update an manager by id
	 * @param managerId
	 * @param managerDetails
	 * @return
	 */
	@PutMapping("/manager/{id}")
	public ResponseEntity<Manager> updateManager(@PathVariable(value = "id") Long managerId, 
	                                       @Valid @RequestBody Manager managerDetails) {
		Manager manager = managerService.findById(managerId);
		if(manager == null) {
	        return ResponseEntity.notFound().build();
	    }
		
	    managerDetails.setId(managerId);
		
	    Manager updatedManager = managerService.update(managerDetails);
	    return ResponseEntity.ok(updatedManager);
	}
	
	/**
	 * Delete a manager by id
	 * @param managerId
	 * @return
	 */
	@DeleteMapping("/manager/{id}")
	public ResponseEntity<Manager> deleteManager(@PathVariable(value = "id") Long managerId) {
	    Manager manager = managerService.findById(managerId);
	    if(manager == null) {
	        return ResponseEntity.notFound().build();
	    }

	    managerService.delete(manager);
	    return ResponseEntity.ok().build();
	}
}
