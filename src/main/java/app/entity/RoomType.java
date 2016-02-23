package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="room_type")
public class RoomType {

	@Id
	@GeneratedValue
	@Column(name="room_type_id")
	private long roomTypeId;

	@Column(name = "name", unique = true, nullable = false, length = 40)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy="roomType", fetch = FetchType.EAGER)
	private List<Room> rooms;

	// constructor


	// getters & setters
	public long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
