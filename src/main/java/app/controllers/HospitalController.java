package app.controllers;

import app.entity.Hospital;
import app.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value="/hospital")
//@RolesAllowed(value = "ADMIN")
public class HospitalController extends AbstractController {

	@Autowired
	private HospitalService hospitalService;

	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ControllerResponse list(){
		return response(hospitalService.list());
	}

	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ControllerResponse findById(@PathVariable long id){
		return response(hospitalService.findById(id));
	}
}
