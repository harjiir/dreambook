package hh.DreamBook.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity represents a table in a relational database
@Entity
public class DreamType {

	// @Id = creating id column of the table
	// @GeneratedValue = generates automatically a unique primary key for every new
	// entity object
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeId;

	@Size(min = 2, max = 30)
	private String typeName;

	// Otherwise entity relationship will cause endless loop
	@JsonIgnore
	// @ManyToOne or @OneToMany = defines a relationship between two entities
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dreamType")
	private List<Dream> dreams;

	public DreamType() {
		super();
	}

	public DreamType(String typeName) {
		super();
		this.typeName = typeName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	// getters and setters for dreams
	public List<Dream> getDreams() {
		return dreams;
	}

	public void setDreams(List<Dream> dreams) {
		this.dreams = dreams;
	}

	@Override
	public String toString() {
		// Do not insert list attribute dreams here! Otherwise --> infinite loop
		return "DreamType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
}
