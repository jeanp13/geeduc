package app.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

import javax.persistence.*;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="hospitalId")
@Table(name="hospital")
public class Hospital {

	@Id
	@GeneratedValue
	@Column(name="hospital_id")
	private long hospitalId;

	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy="hospital")
	private List<User> users;

	@OneToMany(mappedBy="hospital")
	@JsonManagedReference
	private List<Room> rooms;


	// constructor
	public Hospital() {}

	// getters & setters
	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
