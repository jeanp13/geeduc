package app.controllers;

import javax.annotation.security.RolesAllowed;

import app.commons.AppMessages;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import app.entity.User;

@RestController
@RequestMapping(value="/user")
public class UserController extends AbstractController {


	@Autowired
	private UserService userService;

	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ControllerResponse list(){
		return response(userService.list());
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ControllerResponse findById(@PathVariable("id") long id){
		return response(userService.findById(id));
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ControllerResponse delete( @PathVariable long id){
		User user = userService.findById(id);
		if(user != null){
			userService.delete(user.getUserId());
			addSuccessMessage(AppMessages.USER_UPDATE_SUCCESS.getMessage());
		} else {
			addErrorMessage(AppMessages.USER_NOT_FOUND.getMessage());
		}
		return response(null);
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResponse update(@RequestBody User user){
		userService.update(user);
		addSuccessMessage(AppMessages.USER_UPDATE_SUCCESS.getMessage());
		return response(null);
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(method = RequestMethod.POST)
	public ControllerResponse save(@RequestBody User user){
		userService.save(user);
		addSuccessMessage(AppMessages.USER_SAVE_SUCCESS.getMessage());
		return response(null);
	}

	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	public ControllerResponse findByUsername(@PathVariable("username") String username){ return response(userService.findByUsername(username));	}
	
}
