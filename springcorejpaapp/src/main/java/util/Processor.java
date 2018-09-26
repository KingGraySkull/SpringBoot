package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pojos.Person;
import pojos.User;
import repository.UserRepo;

@Component
public class Processor 
{
	@Autowired
	private UserRepo table;
	
	public void save(User user) 
	{
		table.save(user);
	}
}
