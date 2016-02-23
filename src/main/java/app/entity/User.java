package app.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private long userId;
	
	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;

	@Column(name = "email", unique = true, nullable = false, length = 200)
	private String email;

	@Column(name = "phone", unique = false, nullable = false, length = 200)
	private String phone;

	@Column(name = "username", unique = true, nullable = false, length = 200)
	private String username;

	@Column(name = "password", nullable = false, length = 200)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns=@JoinColumn(name = "user_id"),
			inverseJoinColumns=@JoinColumn(name = "role_id"))
	private List<Role> roles;

	@ManyToOne
	@JoinColumn(name="hospital_id", referencedColumnName = "hospital_id")
	private Hospital hospital;

	/*
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonManagedReference
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	*/

	// constructor
	public User() {	}

	// getters & setters
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		this.password = hashedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}


}
