//package com.simpleform.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.simpleform.model.UsersModel;
//import com.simpleform.service.UsersService;
//
//
//@Controller
//public class UsersController {
//	
//	@Autowired
//	private UsersService usersService;
//	
//	
//	
//	
//	public UsersController(UsersService usersService) {
//		this.usersService = usersService;
//	}
//	@GetMapping("/register")
//	public String getRegisterPage(Model model) {
//		model.addAttribute("registerRequest", new UsersModel());
//		return "register_page";
//	}
//	@GetMapping("/login")
//	public String getLoginPage(Model model) {
//		model.addAttribute("loginRequest", new UsersModel());
//
//		return "login_page";
//	}
//	@GetMapping("/report")
//	public String getReportPage(Model model) {
//		model.addAttribute("reportRequest",  new UsersModel());
//		
//		return "report";
//	}
////	@GetMapping("/update/{id}")
////	public String getUpdatePageforId(Model model) {
////		model.addAttribute("updateRequest", new UsersModel());
////		return "update";
////	}
////	@PutMapping("/update/{id}")
////	public UsersModel update(@RequestBody UsersModel user, @PathVariable("id") Integer id) {
////		System.out.println("update request: "+ user);
////		return usersService.updateuser(user, id);
////	}
////	
//	
//	@GetMapping("/update/{id}")
//    public String getUpdatePageForId(@PathVariable("id") Integer id, Model model) {
//        UsersModel existingUser = usersService.find(id);
//        if (existingUser == null) {
//            // Handle the case where user with given ID doesn't exist
//            // Redirect to an error page or handle it as per your requirement
//        	return "error_page";
//        }
//        {model.addAttribute("updateRequest", existingUser);
//        return "update";}
//    }
//
//    @PutMapping("update/{id}")
//    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("updateRequest") UsersModel updateRequest) {
//        UsersModel updatedUser = usersService.updateuser(updateRequest, id);
//        if (updatedUser == null) {
//            // Handle the case where the update failed
//            // Redirect to an error page or handle it as per your requirement
//        	return "error_page";
//        }
//        {return "success";} // Redirect to a success page or the desired page after successful update
//    }
//  
//	
//	@PostMapping("/register")
//	public String register(@ModelAttribute UsersModel usersModel) {
//		System.out.println("register request: "+ usersModel);
//		UsersModel registeredUser = usersService.registerUser(usersModel.getFullname(), usersModel.getCity(), usersModel.getMobile(), usersModel.getEmail());
//		if( registeredUser == null)  {return "error_page" ;}
//		else {return "login_page";}
//	}
//	
//	@PostMapping("/login")
//	public String login(@ModelAttribute UsersModel usersModel) {
//		System.out.println("login request: "+ usersModel);
//		UsersModel authenticatedUser = usersService.authenticate(usersModel.getFullname(), usersModel.getMobile());
//		if(authenticatedUser == null)  {return "error_page" ;}
//		else {return "login_page";}}
//	
//	
//
//
//}

package com.simpleform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simpleform.model.UsersModel;
import com.simpleform.service.UsersService;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @GetMapping("/report")
    public String getReportPage(Model model) {
    	
        model.addAttribute("reportRequest", new UsersModel());
        return "report";
    }
    @GetMapping("/search")
    public String searchReport(@RequestParam(name =  "fullname", required = false) String fullname,
    		@RequestParam(name =  "mobile", required = false) String mobile, 
    		@RequestParam(name =  "email", required = false) String email ,
    		@RequestParam(name =  "city", required = false) String city,
    Model model) {
    	      // Process the search logic using the provided variableName
      // Retrieve the relevant data for the report based on the variableName
      List<UsersModel> searchResults;
      // Add the data to the model to be rendered in the report view
      if (fullname != null) {
    	    // Perform search by name
    	    searchResults = usersService.searchEntriesByName(fullname);
    	  } else if (city != null) {
    	    // Perform search by city
    	    searchResults = usersService.searchEntriesByCity(city);
    	  } else if (mobile != null) {
    	    // Perform search by mobile
    	    searchResults = usersService.searchEntriesByMobile(mobile);
    	  } else if (email != null) {
    	    // Perform search by email
    	    searchResults = usersService.searchEntriesByEmail(email);
    	  } else {
    	    // No search criteria specified, return all entries
    	    searchResults = usersService.getAllEntries();
    	  }

      model.addAttribute("searchResults", searchResults);
      // Add other relevant data to the model
      
      // Return the name of the report view template
      return "searchResults";    }
    @GetMapping("userinfoqr/{id}")
    public String getUserInforforQr(@PathVariable("id") Integer id, Model model) {
    	UsersModel user = usersService.find(id);
    	model.addAttribute("User1", user);
    	return "userinfoqr";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePageForId(@PathVariable("id") Integer id, Model model) {
        UsersModel existingUser = usersService.find(id);
        if (existingUser == null) {
            // Handle the case where user with given ID doesn't exist
            // Redirect to an error page or handle it as per your requirement
            return "error_page";
        }
        model.addAttribute("updateRequest", existingUser);
        return "update";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("updateRequest") UsersModel updateRequest) {
        UsersModel updatedUser = usersService.updateuser(updateRequest, id);
        if (updatedUser == null) {
            // Handle the case where the update failed
            // Redirect to an error page or handle it as per your requirement
            return "error_page";
        }
        return "success"; // Redirect to a success page or the desired page after a successful update
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel) {
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getFullname(), usersModel.getCity(), usersModel.getMobile(), usersModel.getEmail());
        if (registeredUser == null) {
            return "error_page";
        } else {
            return "login_page";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel) {
        System.out.println("login request: " + usersModel);
        List<UsersModel> authenticatedUser = usersService.authenticate(usersModel.getFullname(), usersModel.getMobile());
        if (authenticatedUser == null) {
            return "error_page";
        } else {
            return "login_page";
        }
    }
    
}

