package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import entities.Users;
import repositories.UsersRepository;

@Service
public class UserService
{
	@Autowired
	private UsersRepository usersTable;

	public void saveUsers(Users user)
	{
		user.setPassword( getEncoder().encode(user.getPassword()) );
		System.out.println("Encoded Password "+user.getPassword());
		Users savedUser = usersTable.save(user);
		System.out.println("Saved user "+savedUser.toString());
	}
	
	public Users findUser(String email,String password)
	{
		Users user = usersTable.findByEmail(email);
		return user;
	}
	
	@Bean
	public BCryptPasswordEncoder getEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
