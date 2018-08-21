package events;

import org.springframework.context.ApplicationEvent;

import entities.Users;

public class OnRegistrationEvent extends ApplicationEvent
{
	private static final long serialVersionUID = 1L;
	
	private Users user;
	
	public OnRegistrationEvent(Users user)
	{
		super(user);
		this.user = user;
	}
	
	public Users getUser()
	{
		return this.user;
	}

}
