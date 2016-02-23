package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="precaution")
public class Precaution {

	@Id
	@GeneratedValue
	@Column(name="precaution_id")
	private long precautionId;

	@Column(name = "name", unique = true, nullable = false, length = 40)
	private String name;


	@ManyToMany(mappedBy = "transferPrecautions", fetch = FetchType.LAZY)
	private List<Transfer> transfers;

	/*
	@ManyToMany(mappedBy = "stayPrecautions", fetch = FetchType.LAZY)
	private List<Stay> stays;
	*/

	// constructor


	// getters & setters


	public long getPrecautionId() {
		return precautionId;
	}

	public void setPrecautionId(long precautionId) {
		this.precautionId = precautionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}

	/*
	public List<Stay> getStays() {
		return stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}
	*/
}
