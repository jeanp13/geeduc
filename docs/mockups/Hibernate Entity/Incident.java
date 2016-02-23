package app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="incident")
public class Incident {

	@Id
	@GeneratedValue
	@Column(name="incident_id")
	private long incidentId;
	
	@Column(name = "incident_date", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date incidentDate;
	
	@Column(name = "registered_date", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date registeredDate;
	
	@Column(name = "description", unique = false, nullable = false)
	private String description;
	
	@Column(name = "status", unique = false, nullable = false, length = 1)
	private boolean status;
	
	@OneToOne
	@JoinColumn(name="tutor_id")
	private Tutoring tutor;
	
	@OneToOne
	@JoinColumn(name="subtype_id")
	private Subtype subtype;
	
	@OneToMany(mappedBy="incident")
	private List<Comment> comments;
	
	
}
