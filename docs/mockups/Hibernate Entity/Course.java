package app.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue
	@Column(name="course_id")
	private long CourseId;
	
	@Column(name = "name", unique = false, nullable = false, length = 45)
	private String name;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@ManyToOne(optional = false)
	@JoinColumn(name="user_id")
	private User user;

	
	@OneToMany(mappedBy="course")
	private List<TutorCourse> tutorCourses;


	public long getCourseId() {
		return CourseId;
	}


	public void setCourseId(long courseId) {
		CourseId = courseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<TutorCourse> getTutorCourse() {
		return tutorCourses;
	}


	public void setTutorCourse(List<TutorCourse> tutorCourse) {
		this.tutorCourses = tutorCourse;
	}
		
	
	
	
	
}
