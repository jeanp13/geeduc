package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="transfer")
public class Transfer {

	@Id
	@GeneratedValue
	@Column(name="transfer_id")
	private long transferId;

	@Column(name = "requestDame", unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date requestDate;

	@Column(name = "pre_diagnosis")
	private String preDiagnosis;

	@Column(name = "specialty")
	private String specialty;

	@OneToOne
	@JoinColumn(name="patient_id")
	private Patient patient;

	@OneToOne
	@JoinColumn(name="room_type_id")
	private RoomType roomType;

	@OneToOne
	@JoinColumn(name = "requester_id", referencedColumnName = "user_id")
	private User requester;

	@OneToOne
	@JoinColumn(name = "assignor_id", referencedColumnName = "user_id")
	private User assignor;

	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "transfer_precautions",
			joinColumns=@JoinColumn(name = "transfer_id"),
			inverseJoinColumns=@JoinColumn(name = "precaution_id"))
	private List<Precaution> transferPrecautions;

	//constructors

	
	// getters & setters

	public long getTransferId() {
		return transferId;
	}

	public void setTransferId(long transferId) {
		this.transferId = transferId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getPreDiagnosis() {
		return preDiagnosis;
	}

	public void setPreDiagnosis(String preDiagnosis) {
		this.preDiagnosis = preDiagnosis;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}


	public User getAssignor() {
		return assignor;
	}

	public void setAssignor(User assignor) {
		this.assignor = assignor;
	}

	public List<Precaution> getTransferPrecautions() {
		return transferPrecautions;
	}

	public void setTransferPrecautions(List<Precaution> transferPrecautions) {
		this.transferPrecautions = transferPrecautions;
	}
}
