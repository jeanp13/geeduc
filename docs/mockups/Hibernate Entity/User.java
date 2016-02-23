package app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private long userId;
	
	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;
	
	@Column(name = "username", unique = true, nullable = false, length = 200)
	private String username;
	
	@Column(name = "password", nullable = false, length = 200)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false, length = 200)
	private String email;
	
	@Column(name = "phone", unique = false, nullable = false, length = 200)
	private String phone;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns=@JoinColumn(name = "user_id"),
			inverseJoinColumns=@JoinColumn(name = "role_id"))
	private List<Role> roles;

	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "user_types",
		joinColumns=@JoinColumn(name = "user_id"),
		inverseJoinColumns=@JoinColumn(name = "type_id"))
	private Set<Type> types;
	
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	// constructors
	public User() {
		this.roles = new ArrayList<Role>();
	}

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

	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}
}
