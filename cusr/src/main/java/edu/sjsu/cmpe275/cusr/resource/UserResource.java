package edu.sjsu.cmpe275.cusr.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.cusr.model.User;
import edu.sjsu.cmpe275.cusr.service.UserService;

@RestController
@CrossOrigin
public class UserResource {
	
	@Autowired
	UserService userService;
	
	/** To save the FirebaseID of the user in the database when a new account is created
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.POST, value="/user")
	public String addUserToDatabase(@RequestParam String userId, @RequestParam String emailAddress){
		
		User user = new User();
		user.setFirebaseId(userId);
		user.setEmailAddress(emailAddress);
		
		userService.saveUser(user);
		
		return "XYZ";
	}
}
