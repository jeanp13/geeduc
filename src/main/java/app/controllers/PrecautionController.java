package app.controllers;

import app.commons.AppMessages;
import app.entity.Precaution;
import app.entity.User;
import app.services.PrecautionService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value="/precaution")
public class PrecautionController extends AbstractController {


	@Autowired
	private PrecautionService precautionService;

	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ControllerResponse list(){
		return response(precautionService.list());
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ControllerResponse findById(@PathVariable("id") long id){
		return response(precautionService.findById(id));
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ControllerResponse delete( @PathVariable long id){
		Precaution precaution = precautionService.findById(id);
		if(precaution != null){
			precautionService.delete(precaution.getPrecautionId());
			addSuccessMessage(AppMessages.PRECAUTION_DELETE_SUCCESS.getMessage());
		} else {
			addErrorMessage(AppMessages.PRECAUTION_NOT_FOUND.getMessage());
		}
		return response(null);
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResponse update(@RequestBody Precaution precaution){
		precautionService.update(precaution);
		addSuccessMessage(AppMessages.PRECAUTION_UPDATE_SUCCESS.getMessage());
		return response(null);
	}


	@RolesAllowed(value = "ADMIN")
	@RequestMapping(method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResponse save(@RequestBody Precaution precaution){
		precautionService.save(precaution);
		addSuccessMessage(AppMessages.PRECAUTION_SAVE_SUCCESS.getMessage());
		return response(null);
	}

}
