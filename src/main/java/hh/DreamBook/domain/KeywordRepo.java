package hh.DreamBook.domain;

import org.springframework.data.repository.CrudRepository;

public interface KeywordRepo extends CrudRepository<Keyword, Long> {
	// extending CrudRepository the KeywordRepo inherits
	// methods for example saving, deleting, and finding Keyword entities
}
