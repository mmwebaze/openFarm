package org.bashemera.openfarm.service.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.service.RoleService;
import org.bashemera.openfarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OpenFarmUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//User user = userService.findByEmail(email);
		User user = userService.findByEmailAndEnabled(email, true);
		System.out.println(user);
		if(user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
			return buildUserForAuthentication(user, user.isEnabled(), authorities);
		}
		else {
			throw new UsernameNotFoundException("username not found");
		}
	}
	
	public User findUserByEmail(String email) {
	    return userService.findByEmail(email);
	}
	
	public void saveUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    Role userRole = roleService.findByName("ADMIN");
	    user.setRole(userRole);
	    userService.save(user);
	}
	
	private List<GrantedAuthority> getUserAuthority(Role userRole) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    roles.add(new SimpleGrantedAuthority(userRole.getName()));

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, boolean isEnabled, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), isEnabled, true, true, true, authorities);
	}
}
