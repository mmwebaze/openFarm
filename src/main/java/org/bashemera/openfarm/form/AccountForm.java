package org.bashemera.openfarm.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;


public class AccountForm {
	@Id
	private String id;
	@Size(min=1, max=32, message="The account name must be between 1 and 32 characters")
	private String name;
	@NotNull(message="Please enter unique code")
	@Size(min=3, max=30, message="The code must be between 3 and 32 characters")
	private String code;
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Email address is invalid")
	private String email;
	@Size(min=1, max=32, message="The first name must be between 1 and 32 characters")
	private String firstName;
	@Size(min=1, max=32, message="The last name must be between 1 and 32 characters")
	private String lastName;
	@Size(min=8, max=32, message="The password must be between 8 and 32 characters")
	private String password;
	@NotNull(message="Password and confirm password not a match")
	private String confirmPassword;
	private boolean status = false;
	private String token;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code.replaceAll("\\s+", "_").toLowerCase();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		checkPassword();
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		//checkPassword();
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private void checkPassword() {
		if (this.password == null || this.confirmPassword == null)
			return;
		else if(!this.password.equals(this.confirmPassword)) {
			System.out.println("Checking this just");
			this.confirmPassword = null;
		}
	}
}
