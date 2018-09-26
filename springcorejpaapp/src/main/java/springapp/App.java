package springapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojos.User;
import util.Config;
import util.Processor;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext factory = new AnnotationConfigApplicationContext(Config.class);
    	
    	Processor proc = factory.getBean(Processor.class);
    	
    	proc.save(new User("common user",24));
    }
}
