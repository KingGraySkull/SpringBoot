package controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Users;
import repositories.UsersRepository;

@RestController
@RequestMapping("api")
public class UserController
{
	@Autowired
	private UsersRepository usersTable;
	
	@GetMapping(value = "users")
	public ResponseEntity<List<Users>> getAllUsers()
	{
		List<Users> listOfUsers = usersTable.findAll();
		
		ResponseEntity<List<Users>> response = new ResponseEntity<>(listOfUsers, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<Users> findUserById(@PathVariable("id") Integer id)
	{
		//Users user = usersTable.findById(id);
		Optional<Users> user = usersTable.findById(id);
		ResponseEntity<Users> response;
		if(!user.isPresent())	
		{
			Users defaultUser = new Users("","","","");
			response = new ResponseEntity<>(defaultUser,HttpStatus.BAD_REQUEST);
			return response;
		}
		response = new ResponseEntity<>(user.get(), HttpStatus.OK);
		
		return response;
	}
	
	//add user
	@PostMapping(value = "/add/user/")
	public ResponseEntity<Void> addUser(@RequestBody Users user)
	{	
		Users newUser = new Users(user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
		usersTable.save(newUser);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	//update user
	

	//delete user
}
