package app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tutor_corse")
public class TutorCourse {

	@Id
	@GeneratedValue
	@Column(name="tutor_course_id")
	private long tutorCorseId;
	
	@Column(name ="label", unique = false, nullable = false, length = 10)
	private String label;
	
	@Column(name="cycle", unique = false, nullable = false, length = 1)
	private int cycle; 
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="tutor_id")
	private Tutoring tutor;
	
	
	
}
