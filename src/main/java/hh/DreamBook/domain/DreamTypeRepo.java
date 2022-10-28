package hh.DreamBook.domain;

import org.springframework.data.repository.CrudRepository;

public interface DreamTypeRepo extends CrudRepository<DreamType, Long> {
	// extending CrudRepository the DreamTypeRepo inherits
	// methods for example saving, deleting, and finding DreamType entities
}
