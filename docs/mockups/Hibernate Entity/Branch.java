package app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="branch")
public class Branch {

	@Id
	@GeneratedValue
	@Column(name="branch_id")
	private long branchId;
	
	@Column(name = "name", unique = false, nullable = false, length = 200)
	private String name;

	// contructors
	public Branch() {}
	
	// getters & setters
	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
