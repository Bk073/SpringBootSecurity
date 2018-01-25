package com.shopping.enitites;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails { 
	 
    public CustomUserDetails(final User users) {
        super(users);
    } 
 
    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities() {
 
        /* getRoles() 
                .stream() 
                .map(role -> new SimpleGrantedAuthority("ROLE_" + user.getRole())) 
                .collect(Collectors.toList());
    	
    	 Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
         for(User role : role.getRole() ){
             GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getUserName());
             authorities.add(grantedAuthority);
         }

         return authorities;*/
    	return null;
    } 
    public List<GrantedAuthority> getAuthorities(String role) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role == "ADMIN") {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role == "USER") {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authList;
        }
 
    @Override 
    public String getPassword() {
        return super.getPassword(); 
    } 
 
    @Override 
    public String getUsername() {
        return super.getUserName(); 
    } 
 
    @Override 
    public boolean isAccountNonExpired() { 
        return true; 
    } 
 
    @Override 
    public boolean isAccountNonLocked() { 
        return true; 
    } 
 
    @Override 
    public boolean isCredentialsNonExpired() { 
        return true; 
    } 
 
    @Override 
    public boolean isEnabled() { 
        return true; 
    } 
} 