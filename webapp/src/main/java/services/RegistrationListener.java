package services;

import org.springframework.context.ApplicationListener;

import events.OnRegistrationEvent;

public class RegistrationListener implements ApplicationListener<OnRegistrationEvent>
{
	@Override
	public void onApplicationEvent(OnRegistrationEvent event)
	{
		sendRegistrationEmail(event);
	}

	private void sendRegistrationEmail(OnRegistrationEvent e)
	{	
	}
}
