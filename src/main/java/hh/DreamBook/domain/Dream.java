package hh.DreamBook.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity represents a table in a relational database
@Entity
public class Dream {
	// @Id = creating id column of the table
	// @GeneratedValue = generates automatically a unique primary key for every new
	// entity object
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dreamId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dreamDate;
	private String dreamDescription;

	// @ManyToOne or @OneToMany = defines a relationship between two entities
	// @JoinColumn = defines the owner of the relationship
	// DreamType typeId is now FK in Dream table)
	@ManyToOne
	@JsonIgnoreProperties("dreams")
	@JoinColumn(name = "typeId")
	private DreamType dreamType;

	@ManyToOne
	@JoinColumn(name = "keyId")
	private Keyword keyword;

	public Dream() {
	}

	public Dream(LocalDate dreamDate, String dreamDescription, DreamType dreamType, Keyword keyword) {
		super();
		this.dreamDate = dreamDate;
		this.dreamDescription = dreamDescription;
		this.dreamType = dreamType;
		this.keyword = keyword;
	}

	public Long getDreamId() {
		return dreamId;
	}

	public LocalDate getDreamDate() {
		return dreamDate;
	}

	public String getDreamDescription() {
		return dreamDescription;
	}

	public DreamType getDreamType() {
		return dreamType;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setDreamId(Long dreamId) {
		this.dreamId = dreamId;
	}

	public void setDreamDate(LocalDate dreamDate) {
		this.dreamDate = dreamDate;
	}

	public void setDreamDescription(String dreamDescription) {
		this.dreamDescription = dreamDescription;
	}

	public void setDreamType(DreamType dreamType) {
		this.dreamType = dreamType;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	// toString
	@Override
	public String toString() {
		return "Dream [dreamId=" + dreamId + ", dreamDate=" + dreamDate + ", dreamDescription=" + dreamDescription
				+ ", dreamType=" + this.getDreamType() + ", keyword=" + this.getKeyword() + "]";
	}

}
