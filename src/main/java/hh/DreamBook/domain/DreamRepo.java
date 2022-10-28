package hh.DreamBook.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DreamRepo extends CrudRepository<Dream, Long> {
	// extending CrudRepository the DreamRepo inherits
	// methods for example saving, deleting, and finding Dream entities

	// using queries from the CrudRepository by using Rest API
	List<Dream> findByDreamDate(@Param("dreamDate") LocalDate dreamDate);
}
