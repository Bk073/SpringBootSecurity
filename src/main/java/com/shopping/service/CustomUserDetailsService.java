package com.shopping.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.enitites.CustomUserDetails;
import com.shopping.enitites.User;
import com.shopping.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	 @Autowired 
	    private UserRepository usersRepository;
	 
	 
	    @Override 
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = usersRepository.findByUserName(username);
	 
	        /*optionalUsers 
	                .orElseThrow(() -> new UsernameNotFoundException("Username not found")); 
	        return optionalUsers 
	                .map(CustomUserDetails::new).get(); */
	        if(user == null){
	        	throw new UsernameNotFoundException("Username" + username+" not found");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),user.getRole());
	    } 
	    private Collection<GrantedAuthority> getGrantedAuthorities(User user){
	    	Collection<GrantedAuthority> grantedAuthorities = new 	ArrayList<>();
	    	if(user.getRole().equals("ADMIN")){
	    		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    	}
	    	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    	return grantedAuthorities;
	    }

}
