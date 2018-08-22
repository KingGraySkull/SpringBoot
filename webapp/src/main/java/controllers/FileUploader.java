package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploader
{
	private static final String uploadedDirectory = "/home/webwerks/Documents/user_uploads";
	
	@GetMapping("/user/upload")
	public String fileUploadForm(HttpSession session)
	{
		if(isSessionAlive(session))			
		{
			return "upload";
		}
		return "login";
	}
	
	@PostMapping("/user/upload/files")
	public String uploadedData(Model model,@RequestParam MultipartFile[] files)
	{
		System.out.println("Total Files "+files.length);
		StringBuilder output = new StringBuilder();
		
		for(MultipartFile file : files)
		{
			System.out.println("Content-Type : "+file.getContentType()+" File name : "+file.getOriginalFilename()+" Size : "+file.getSize());
			output.append(file.getOriginalFilename()+" ");
			Path destinationPath = Paths.get(uploadedDirectory+"/"+file.getOriginalFilename());
			try
			{
				Files.write(destinationPath, file.getBytes());
			} 
			catch (IOException e)
			{
				System.out.println("Error Uploading File "+e);
				model.addAttribute("message","Error uploading file...");
				return "upload";
			}
		}
		model.addAttribute("message","Files "+output+" uploaded successfully");
		return "home";
	}
	
	private boolean isSessionAlive(HttpSession session)
	{
		 System.out.println(" "+session.getAttribute("userkey"));	
		 return (session.getAttribute("userkey") != null);
	}

}
