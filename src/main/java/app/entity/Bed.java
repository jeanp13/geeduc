package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="bed")
public class Bed {

	@Id
	@GeneratedValue
	@Column(name="bed_id")
	private long bedId;

	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;

	@Column(name = "status", unique = false, nullable = false, length = 200)
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="room_id")
	private Room room;

	// constructor


	// getters & setters

	public long getBedId() {
		return bedId;
	}

	public void setBedId(long bedId) {
		this.bedId = bedId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
