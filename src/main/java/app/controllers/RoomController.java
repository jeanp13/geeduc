package app.controllers;

import app.commons.AppMessages;
import app.entity.Room;
import app.entity.User;
import app.services.RoomService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value="/room")
//@RolesAllowed(value = "ADMIN")
public class RoomController extends AbstractController {


	@Autowired
	private RoomService roomService;

	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ControllerResponse list(){
		return response(roomService.list());
	}
	
	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ControllerResponse findById(@PathVariable long id){
		return response(roomService.findById(id));
	}
	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ControllerResponse delete( @PathVariable long id){
		Room room = roomService.findById(id);
		if(room != null){
			roomService.delete(room.getRoomId());
			addSuccessMessage(AppMessages.USER_UPDATE_SUCCESS.getMessage());
		} else {
			addErrorMessage(AppMessages.USER_NOT_FOUND.getMessage());
		}
		return response(null);
	}

	@RolesAllowed(value = "ADMIN")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ControllerResponse update(@RequestBody Room room){
		roomService.update(room);
		addSuccessMessage(AppMessages.USER_UPDATE_SUCCESS.getMessage());
		return response(null);
	}
	
	@RolesAllowed(value = "ADMIN")
	@RequestMapping(method = RequestMethod.POST)
	public ControllerResponse save(@RequestBody Room room){
		roomService.save(room);
		addSuccessMessage(AppMessages.USER_SAVE_SUCCESS.getMessage());
		return response(null);
	}

/*	@RolesAllowed(value = "ADMIN")
	@RequestMapping(value = "/hospital/{id}", method = RequestMethod.GET)
	public ControllerResponse findByHospital(@PathVariable("id") long id){
		return response(roomService.findByHospital(id));
	}*/
	
}
