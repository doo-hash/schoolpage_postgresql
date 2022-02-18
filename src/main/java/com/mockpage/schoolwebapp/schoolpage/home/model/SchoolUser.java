package com.mockpage.schoolwebapp.schoolpage.home.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "schoolUsers")
public class  SchoolUser{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="First Name cannot be empty.")
	@Pattern(regexp = "^[a-zA-Z\s]{2,50}", message = "Must contain only letters.")
	@Size(min = 2, message = "First Name cannot be less than 2 characters.")
	private String firstname;
	
	@NotBlank(message="Last Name cannot be empty.")
	@Pattern(regexp = "^[a-zA-Z\s]{2,50}", message = "Must contain only letters.")
	@Size(min = 2, message = "Last Name cannot be less than 2 characters.")
	private String lastname;
	
	@NotBlank(message="Email cannot be empty.")
	@Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
	message="Invalid email address.")
	@Email
	private String email;
	
	@NotBlank(message="Phone number cannot be empty.")
	@Pattern(regexp = "^[0-9-]{12}", message = "Must contain only numbers.")
	@Size(min = 10, message = "Phone number cannot be less than 10 characters.")
	private String phonenumber;
	
	@NotBlank(message="User id cannot be empty.")
	@Pattern(regexp = "^[a-zA-Z0-9-_]{2,50}", message = "Invalid characters.")
	@Size(min = 3, message = "User id cannot be less than 3 characters.")
	private String userid;
	
	@NotBlank(message="Cannot be empty.")
	@Pattern(regexp = "^[a-zA-Z0-9\s]{2,50}", message = "Invalid characters.")
	@Size(min = 3, message = "Cannot be less than 3 characters.")
	private String designation;
	
	@NotBlank(message="Password cannot be empty.")
	@Size(min = 8, message = "Password cannot be less than 8 characters.")
	private String password;
	
	  @ManyToMany(fetch = FetchType.EAGER)
	  
	  @JoinTable( name = "userroles", joinColumns = @JoinColumn(name =
	  "userid", referencedColumnName = "id"), inverseJoinColumns
	  = @JoinColumn(name = "roleid", referencedColumnName = "id") )
	 
	private Collection<Role> roles;
	
	@AssertTrue(message = "Please check before you proceed.")
	private boolean checkterms;

	private boolean inactive;
	private boolean deleteuser;

	
	public SchoolUser(
			@NotBlank(message = "First Name cannot be empty.") @Pattern(regexp = "^[a-zA-Z ]{2,50}", message = "Must contain only letters.") @Size(min = 2, message = "First Name cannot be less than 2 characters.") String firstname,
			@NotBlank(message = "Last Name cannot be empty.") @Pattern(regexp = "^[a-zA-Z ]{2,50}", message = "Must contain only letters.") @Size(min = 2, message = "Last Name cannot be less than 2 characters.") String lastname,
			@NotBlank(message = "Email cannot be empty.") @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email address.") @Email String email,
			@NotBlank(message = "Phone number cannot be empty.") @Pattern(regexp = "^[0-9-]{12}", message = "Must contain only numbers.") @Size(min = 10, message = "Phone number cannot be less than 10 characters.") String phonenumber,
			@NotBlank(message = "User id cannot be empty.") @Pattern(regexp = "^[a-zA-Z0-9-_]{2,50}", message = "Invalid characters.") @Size(min = 3, message = "User id cannot be less than 3 characters.") String userid,
			@NotBlank(message = "Cannot be empty.") @Pattern(regexp = "^[a-zA-Z0-9 ]{2,50}", message = "Invalid characters.") @Size(min = 3, message = "Cannot be less than 3 characters.") String designation,
			@NotBlank(message = "Password cannot be empty.") @Size(min = 8, message = "Password cannot be less than 8 characters.") String password,
			Collection<Role> roles, @AssertTrue(message = "Please check before you proceed.") boolean checkterms,
			boolean inactive, boolean deleteuser) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.userid = userid;
		this.designation = designation;
		this.password = password;
		this.roles = roles;
		this.checkterms = checkterms;
		this.inactive = inactive;
		this.deleteuser = deleteuser;
	}

	public SchoolUser(
			@NotBlank(message = "First Name cannot be empty.") @Pattern(regexp = "^[a-zA-Z ]{2,50}", message = "Must contain only letters.") @Size(min = 2, message = "First Name cannot be less than 2 characters.") String firstname,
			@NotBlank(message = "Last Name cannot be empty.") @Pattern(regexp = "^[a-zA-Z ]{2,50}", message = "Must contain only letters.") @Size(min = 2, message = "Last Name cannot be less than 2 characters.") String lastname,
			@NotBlank(message = "Email cannot be empty.") @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email address.") @Email String email,
			@NotBlank(message = "Phone number cannot be empty.") @Pattern(regexp = "^[0-9-]{12}", message = "Must contain only numbers.") @Size(min = 10, message = "Phone number cannot be less than 10 characters.") String phonenumber,
			@NotBlank(message = "User id cannot be empty.") @Pattern(regexp = "^[a-zA-Z0-9-_]{2,50}", message = "Invalid characters.") @Size(min = 3, message = "User id cannot be less than 3 characters.") String userid,
			String designation,
			@NotBlank(message = "Password cannot be empty.") @Size(min = 8, message = "Password cannot be less than 8 characters.") String password,
			Collection<Role> roles, @AssertTrue(message = "Please check before you proceed.") boolean checkterms) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.userid = userid;
		this.designation = designation;
		this.password = password;
		this.roles = roles;
		this.checkterms = checkterms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isCheckterms() {
		return checkterms;
	}

	public void setCheckterms(boolean checkterms) {
		this.checkterms = checkterms;
	}

	
	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public boolean isDelete() {
		return deleteuser;
	}

	public void setDelete(boolean deleteuser) {
		this.deleteuser = deleteuser;
	}

	@Override
	public String toString() {
		return "SchoolUser [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phonenumber="
				+ phonenumber + ", userid=" + userid + ", designation=" + designation + ", password=" + password
				+ ", roles=" + roles + ", checkterms=" + checkterms + "]";
	}

	public SchoolUser() {
	}
	
	
}