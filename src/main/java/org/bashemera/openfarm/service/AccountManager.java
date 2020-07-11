package org.bashemera.openfarm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bashemera.openfarm.exception.AccountCreationException;
import org.bashemera.openfarm.form.AccountForm;
import org.bashemera.openfarm.model.Farm;
import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountManager implements AccountService {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	//@Transactional
	public int createAccount(AccountForm account) throws AccountCreationException {
		
		List<User> accountUsers = new ArrayList<>();
		
		Set<Role> newActRoles = new HashSet<Role>(); 
		
		Role managerRole = roleService.findByName("MANAGER");
		
		if (managerRole == null) {
			throw new AccountCreationException("The role MANAGER does not exsist");
		}
		
		newActRoles.add(managerRole);
		  
		User user = new User(); 
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName()); 
		user.setEmail(account.getEmail());
		user.setEnabled(false); 
		user.setRoles(newActRoles);
		user.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		user = userService.save(user);
		
		if (user == null) {
			throw new AccountCreationException("Failed creating MANAGER user");
		}
		
		accountUsers.add(user);
		  
		Farm farm = new Farm(); 
		farm.setName(account.getName());
		farm.setCode(account.getCode()); 
		farm.setEmployees(accountUsers);
		
		farm = farmService.save(farm);
		
		if (farm == null) {
			throw new AccountCreationException("Failed to create ACCOUNT");
		}
		
		return 0;
	}

	@Override
	public int disableAccount(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAcount(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public AccountForm checkPassword(AccountForm accountForm) {
		if (accountForm.getPassword() == null || accountForm.getConfirmPassword() == null) {
			System.out.println("**********");
			return accountForm;
		}
			
		else if(!accountForm.getPassword().equals(accountForm.getConfirmPassword())) {
			System.out.println("^^^^^^^^^^^^^^");
			accountForm.setConfirmPassword(null);
			accountForm.setPassword(null);
			return accountForm;
		}
		System.out.println("????????????????");
		return accountForm;
	}

}
