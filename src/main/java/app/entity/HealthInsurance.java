package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="health_insurance")
public class HealthInsurance {

	@Id
	@GeneratedValue
	@Column(name="health_insurance_id")
	private long healthInsuranceId;

	@Column(name = "name", unique = true, nullable = false, length = 40)
	private String name;

	@OneToMany(mappedBy="healthInsurance", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Patient> patients;

	// constructor


	// getters & setters


	public long getHealthInsuranceId() {
		return healthInsuranceId;
	}

	public void setHealthInsuranceId(long healthInsuranceId) {
		this.healthInsuranceId = healthInsuranceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
}
