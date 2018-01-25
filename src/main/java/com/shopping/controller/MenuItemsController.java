package com.shopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.enitites.MenuItems;
import com.shopping.repository.MenuItemsRepository;

@RestController
@RequestMapping("/api")
public class MenuItemsController {
	@Autowired
	MenuItemsRepository menuItemsRepository;
	
	@GetMapping("/menuItems")
	public List<MenuItems> getAllItems(Model model){
		model.addAttribute("menuItems", new MenuItems());
		return menuItemsRepository.findAll();
	}
	@PostMapping("/menuItems")
	public MenuItems createMenuItems(@ModelAttribute MenuItems menuItems, Model model){
		model.addAttribute("menuItems", menuItems);
		return menuItemsRepository.save(menuItems);
	}
	
	@GetMapping("/menuItems/{itemName}")
	public ResponseEntity<MenuItems> getMenuByName(@PathVariable(value="itemName") String itemName){
		MenuItems menuItems = menuItemsRepository.findOne(itemName);
		if(menuItems == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(menuItems);
	}
	
	@PutMapping("/menuItems/{itemName}")
	public ResponseEntity<MenuItems> updateMenuItems(@PathVariable(value = "itemName") String itemName,@Valid @RequestBody MenuItems itemDetails){
		MenuItems menuItems = menuItemsRepository.findOne(itemName);
		if(menuItems == null){
			return ResponseEntity.notFound().build();
		}
		menuItems.setItemName(itemDetails.getItemName());
		menuItems.setItemPrice(itemDetails.getItemPrice());
		MenuItems updatedItem = menuItemsRepository.save(menuItems);
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/menuItems/{itemName}")
	public ResponseEntity<MenuItems> deleteMenuItems (@PathVariable(value="itemName") String itemName){
		MenuItems menuItems = menuItemsRepository.findOne(itemName);
		if(menuItems == null){
			return ResponseEntity.notFound().build();
		}
		menuItemsRepository.delete(menuItems);
		return ResponseEntity.ok().build();
	}

		
}
