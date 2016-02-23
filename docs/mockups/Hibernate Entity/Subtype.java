package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="subtype")
public class Subtype {

	@Id
	@GeneratedValue
	@Column(name="subtype_id")
	private long subtypeId;
	
	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="type_id")
	private Type type;

	public long getSubtypeId() {
		return subtypeId;
	}

	public void setSubtypeId(long subtypeId) {
		this.subtypeId = subtypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
