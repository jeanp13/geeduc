package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="type")
public class Type {

	@Id
	@GeneratedValue
	@Column(name="type_id")
	private long typeId;
	
	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;

	@ManyToMany(mappedBy = "types", fetch = FetchType.LAZY)
	private List<User> users;

	@OneToMany(mappedBy="type", fetch = FetchType.EAGER)
	@JsonManagedReference
	//private List<Subtype> subtypes;
	private Set<Subtype> subtypes;

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Subtype> getSubtypes() {
		return subtypes;
	}

	public void setSubtypes(Set<Subtype> subtypes) {
		this.subtypes = subtypes;
	}
}
