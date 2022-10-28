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
public class Keyword {

	// @Id = creating id column of the table
	// @GeneratedValue = generates automatically a unique primary key for every new
	// entity object
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long keyId;
	@Size(min = 2, max = 30)
	private String keyName;
	@Size(min = 2, max = 1000)
	private String keyDescription;

	// Otherwise entity relationship will cause endless loop
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "keyword")
	private List<Dream> dreams;

	public Keyword() {
		super();
	}

	public Keyword(String keyName, String keyDescription) {
		super();
		this.keyName = keyName;
		this.keyDescription = keyDescription;
	}

	public Long getKeyId() {
		return keyId;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getKeyDescription() {
		return keyDescription;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setKeyDescription(String keyDescription) {
		this.keyDescription = keyDescription;
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
		return "Keyword [keyId=" + keyId + ", keyName=" + keyName + ", keyDescription=" + keyDescription + "]";
	}
}
