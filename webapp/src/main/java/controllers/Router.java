package controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dtos.UserDto;
import entities.Users;
import services.UserService;


@Controller
public class Router
{	
	@Autowired
	private UserService service;
	
//	@GetMapping("/{template}")
//	public String routePages(@PathVariable String template,HttpSession session)
//	{
//		if(isSessionAlive(session))
//		{
//			return template;
//		}
//		else
//		{
//			return "redirect:/signup";
//		}
//	}
	
	@RequestMapping(value = {"/","home","index"})
	public String homePage()
	{
		return "home";
	}
	
	@GetMapping("/login")
	public String loginPage(HttpSession session)
	{
		if(isSessionAlive(session))
		{
			return "home";
		}
		
		return "login";
	}
	
	@GetMapping("/user/signout")
	public String signout(HttpSession session)
	{
		if(isSessionAlive(session))
		{
			session.invalidate();
		}
		else
		{
			return "login";	
		}
		
		return "redirect:/home";
	}
	
	@PostMapping("/user/signin")
	public String signin( @RequestParam("userEmail") String userEmail,
						  @RequestParam("userPassword") String userPassword,
						  Model model,
						  HttpSession session)
	{
		Users user = null;
		if(isSessionAlive(session))
		{
			return "redirect:/home";
		}
		else
		{
			System.out.println("Email "+userEmail);
			user = service.findUser(userEmail, userPassword);
			
			if(user == null)
			{
				model.addAttribute("loginError","You are not registered");
				return "login";
			}
			else if(!service.getEncoder().matches( userPassword, user.getPassword() ))
			{
				model.addAttribute("loginError","Invalid Email or Password");
				return "login";
			}
			session.setAttribute("userkey", ""+user.getUserId());
		}
		return "redirect:/home";
	}	
	
	@GetMapping("signup")
	public String registrationPage(Model model,HttpSession session)
	{
		if(isSessionAlive(session))
		{
			return "redirect:/home";
		}
		UserDto user = new UserDto();
		model.addAttribute("userDto",user);
		return "signup";
	}
	
	
	
	@PostMapping("signupUser")
	public String onSignup(	@Valid @ModelAttribute("userDto") UserDto userDto,
							BindingResult result,
							Model model,
							HttpSession session)
	{
		if(result.hasErrors())
		{
			System.out.println("Error "+userDto.toString());
			return "signup";
		}
		else
		{
			Users user = new Users(	userDto.getFirstName(),
								   	userDto.getLastName(),
								   	userDto.getPassword(),
								   	userDto.getEmail());
			System.out.println("User Password "+user.getPassword());
			service.saveUsers(user);
			System.out.println("userId "+user.getUserId());
			session.setAttribute("userkey",user.getUserId());
		}
		
		System.out.println("Save user "+userDto.toString());
	
		model.addAttribute("registrationMessage", "You have registered successfully");
		return "redirect:/home";
	}
	
	private boolean isSessionAlive(HttpSession session)
	{
		 System.out.println(" "+session.getAttribute("userkey"));	
		 return (session.getAttribute("userkey") != null);
	}

}
