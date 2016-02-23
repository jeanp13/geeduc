package app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue
	@Column(name="comment_id")
	private long commentId;
	
	@Column(name = "date", unique = false, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name ="text", unique = false, nullable = false)
	private String text;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="incident_id")
	private Incident incident;
	
	
	
}
