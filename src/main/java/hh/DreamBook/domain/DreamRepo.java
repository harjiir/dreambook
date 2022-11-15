package hh.DreamBook.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DreamRepo extends CrudRepository<Dream, Long> {

	// extending CrudRepository the DreamRepo inherits
	// methods for example saving, deleting, and finding Dream entities
}
