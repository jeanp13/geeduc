package app.controllers;

import app.entity.Role;
import app.entity.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value="/role")
//@RolesAllowed(value = "ADMIN")
public class RoleController extends AbstractController {

	@Autowired
	private RoleRepository roleRepository;

	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ControllerResponse list(){
		Iterable<Role> list = roleRepository.findAll();
		return response(list);
	}
	
	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ControllerResponse findById(@PathVariable long id){
		
		Role role  = roleRepository.findOne(id);
		return response(role);
		
	}

}
