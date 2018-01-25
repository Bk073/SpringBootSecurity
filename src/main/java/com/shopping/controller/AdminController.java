package com.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.enitites.MenuItems;
import com.shopping.repository.MenuItemsRepository;

@Controller
@RequestMapping(value="admin")
public class AdminController {
	@Autowired
	MenuItemsRepository menuItemsRepository;
	@GetMapping("/admin")
	public String adminPanel(){
		return "admin";
	}
	/*@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String displayLogin(Model model) { 
	    model.addAttribute("menuItems", new MenuItems()); 
	    return "login"; 
	}*/
	@PostMapping("/menuItems")
	public MenuItems createMenuItems(@ModelAttribute("menuItems") MenuItems menuItems, BindingResult bindingResult, @RequestParam String action){
		switch(action.toLowerCase()){
		case "add":
			return menuItemsRepository.save(menuItems);
		case "delete":
			 menuItemsRepository.delete(menuItems);
		case "find":
			return menuItemsRepository.findOne("menuItems");
		default:
			return menuItemsRepository.save(menuItems);
		}
		
	}
	
}
