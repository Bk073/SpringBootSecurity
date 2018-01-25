package com.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shopping.enitites.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	User findByUserName(String userName);
}
