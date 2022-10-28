package hh.DreamBook.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
	// new query
	User findByUsername(String username);
}
