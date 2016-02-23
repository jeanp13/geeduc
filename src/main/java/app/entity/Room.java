package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="room")
public class Room {

	@Id
	@GeneratedValue
	@Column(name="room_id")
	private long roomId;

	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="hospital_id")
	@JsonBackReference
	private Hospital hospital;


	@OneToMany(mappedBy="room", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Bed> beds;

	@ManyToOne(fetch = FetchType.EAGER)
	//@JsonBackReference
	@JoinColumn(name="room_type_id")
	private RoomType roomType;

	// constructor

	public Room() {	}


	// getters & setters


	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Bed> getBeds() {
		return beds;
	}

	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
}
