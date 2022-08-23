package com.flash.app.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@TwoFieldsAreEqual(fieldOneName = "confirmPassword", fieldTwoName = "password", message = "Password and Conform Password must be the same.")
public class CreateNormalUserForm {
	
	@NotEmpty(message = "Email can not be empty.")
	//@Email(message = "@Email is invalid")
	@Pattern(regexp = "^.*@.*$", message = "Email @Pattern format invalid")
//	@EmailUnique(message = "Email exists in database")
	private String email;

	@NotEmpty(message = "Password can not be empty.")
	@Size(min = 10, max = 25, message = "Password must be between 10 and 25 characters")
	@Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain at least one digit 0 through 9")
	@Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain at least one lowercase letter")
	@Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain at least one uppercase letter")
	@Pattern(regexp = "(?=.*[!@#\\$%\\^&\\*]).+", message = "Password must contain at least one special character ! @ # $ % ^ & *")
	private String password;

	@NotEmpty(message = "Confirm Password is required.")
	private String confirmPassword;

	@NotEmpty(message = "Full name needed what ever you want to put.")
	private String fullName;

	@NotEmpty(message = "Address needed what ever you want to put.")
	private String address;

	@NotEmpty(message = "Phone needed what ever you want to put.")
	private String phone;

	@NotEmpty(message = "Amount needed what ever you want to put.")
	private String amount;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CreateUserForm [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", fullName=" + fullName + ", phone=" + phone + ", address=" + address + ", amount=" + amount + "]";
	}
}