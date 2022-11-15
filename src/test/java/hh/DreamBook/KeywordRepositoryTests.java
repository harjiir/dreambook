package hh.DreamBook;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.DreamBook.domain.Keyword;
import hh.DreamBook.domain.KeywordRepo;

//annotation tells JUnit to run using Spring’s testing support
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class KeywordRepositoryTests {

	@Autowired
	private KeywordRepo kRepo;

	@Test
	public void addNewKeywordTest() {

		// create test key
		Keyword key = new Keyword("Test key", "This is description for test key");
		kRepo.save(key);

		// see that key is not null
		assertNotNull(key);

		// test that new keyword is found in repository
		Iterable<Keyword> keys = kRepo.findAll();
		assertThat(keys.toString().contains("Test key"));

		// test that key name and descriptions' last letter equals what we are expecting
		assertEquals("Test key", key.getKeyName());
		assertThat(key.getKeyDescription().endsWith("y"));
	}

	@Test
	public void findKeywordTest() {

		// find all types from dreamtype repository
		Iterable<Keyword> keys = kRepo.findAll();
		// test that key list is not null
		assertNotNull(keys);

		// test that key list size is 14
		assertThat(keys).hasSize(14);
		// check that key id 15 is really empty
		Optional<Keyword> id15 = kRepo.findById(15L);
		assertThat(id15.isEmpty());

		// test that key list contains for example following keys
		assertThat(keys.toString()).contains("Parade", "Ice", "Falling", "Beach");
	}

	@Test
	public void deleteKeywordTest() {

		// find all types from type repository
		Iterable<Keyword> keys = kRepo.findAll();
		// test that type list is not null
		assertNotNull(keys);

		// get some type by id
		Optional<Keyword> keyInDelete = kRepo.findById(10L);
		// check that id is not null
		assertNotNull(keyInDelete);

		// delete type by id
		kRepo.deleteById(10L);

		// test that deleted type is not on the list anymore
		Optional<Keyword> keyWasDeleted = kRepo.findById(10L);
		assertThat(keyWasDeleted.isEmpty());
	}
}
