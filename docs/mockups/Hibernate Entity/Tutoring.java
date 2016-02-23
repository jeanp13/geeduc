package app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tutoring")
public class Tutoring {

	@Id
	@GeneratedValue
	@Column(name="tutor_id")
	private long tutorId;
	
	@OneToOne 
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name = "year", unique = false, nullable = false, length = 4)
	private int year;
	
	@Column(name ="semester", unique = false, nullable = false, length = 1)
	private int semester; 
	
	@OneToMany(mappedBy="tutor")
	private List<TutorCourse> tutorCourses;

	public long getTutorId() {
		return tutorId;
	}

	public void setTutorId(long tutorId) {
		this.tutorId = tutorId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<TutorCourse> getTutorCourse() {
		return tutorCourses;
	}

	public void setTutorCourse(List<TutorCourse> tutorCourse) {
		this.tutorCourses = tutorCourse;
	}
	
	
	
}
