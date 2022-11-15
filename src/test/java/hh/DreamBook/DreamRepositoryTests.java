package hh.DreamBook;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.DreamBook.domain.Dream;
import hh.DreamBook.domain.DreamRepo;
import hh.DreamBook.domain.DreamType;
import hh.DreamBook.domain.DreamTypeRepo;
import hh.DreamBook.domain.Keyword;
import hh.DreamBook.domain.KeywordRepo;

// annotation tells JUnit to run using Spring’s testing support
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DreamRepositoryTests {

	@Autowired
	private DreamTypeRepo dtRepo;

	@Autowired
	private DreamRepo dRepo;

	@Autowired
	private KeywordRepo kRepo;

	@Test
	public void addNewDreamTest() {

		// Test data
		LocalDate date = LocalDate.of(2021, 12, 28);
		DreamType type = new DreamType("Test type", "Test type description");
		Keyword key = new Keyword("Test key", "Test key description");
		dtRepo.save(type);
		kRepo.save(key);

		// Save new test dream
		Dream testDream = new Dream(date, "Test dream description", type, key);
		dRepo.save(testDream);

		// get all dreams
		Iterable<Dream> dreams = dRepo.findAll();
		// test that dream list is not null
		assertNotNull(dreams);

		// test that new dream is found in repository
		assertThat(dreams.toString().contains("Test dream description"));

		// test for example that date, type and key matches what we are expecting
		assertEquals(date, testDream.getDreamDate());
		assertEquals(type, testDream.getDreamType());
		assertEquals(key, testDream.getKeyword());
	}

	@Test
	public void findDreamsTest() {

		// find all dreams from dream repository
		Iterable<Dream> dreams = dRepo.findAll();
		// test that dream list is not null
		assertNotNull(dreams);

		// test that dream list size is four
		assertThat(dreams).hasSize(4);
		// check that type id 5 is really empty
		Optional<Dream> id5 = dRepo.findById(5L);
		assertThat(id5.isEmpty());
	}

	@Test
	public void deleteDreamTest() {

		// find all dreams from dream repository
		Iterable<Dream> dreams = dRepo.findAll();
		// test that dream list is not null
		assertNotNull(dreams);

		// get some dream by id 26
		Optional<Dream> dreamInDelete = dRepo.findById(26L);
		// check that id is not null
		assertNotNull(dreamInDelete);

		dRepo.deleteById(26L);
		Optional<Dream> dreamWasDeleted = dRepo.findById(26L);
		assertThat(dreamWasDeleted.isEmpty());
	}
}
