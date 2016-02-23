package app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="stay")
public class Stay {

	@Id
	@GeneratedValue
	@Column(name="stay_id")
	private long stay;

	@Column(name = "admission_date", unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date admissionDate;

	@Column(name = "discharge_date", unique = true, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dischargeDate;

	@Column(name = "provenance")
	private String provenance;

	@Column(name = "internal_transfer")
	private String internalTransfer;

	@OneToOne
	@JoinColumn(name="patient_id")
	private Patient patient;

	@OneToOne
	@JoinColumn(name="bed_id")
	private Bed bed;

	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "stay_precautions",
			joinColumns=@JoinColumn(name = "stay_id"),
			inverseJoinColumns=@JoinColumn(name = "precaution_id"))
	private List<Precaution> stayPrecautions;

	//constructors

	
	// getters & setters

	public long getStay() {
		return stay;
	}

	public void setStay(long stay) {
		this.stay = stay;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getInternalTransfer() {
		return internalTransfer;
	}

	public void setInternalTransfer(String internalTransfer) {
		this.internalTransfer = internalTransfer;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Precaution> getStayPrecautions() {
		return stayPrecautions;
	}

	public void setStayPrecautions(List<Precaution> stayPrecautions) {
		this.stayPrecautions = stayPrecautions;
	}
}
