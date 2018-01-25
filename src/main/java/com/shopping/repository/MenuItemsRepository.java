package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.enitites.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems,String>{

}
