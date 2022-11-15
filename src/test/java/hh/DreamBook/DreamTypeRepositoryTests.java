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

import hh.DreamBook.domain.DreamType;
import hh.DreamBook.domain.DreamTypeRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DreamTypeRepositoryTests {

	@Autowired
	private DreamTypeRepo dtRepo;

	@Test
	public void addNewDreamTypeTest() {
		// test type
		DreamType type = new DreamType("Test type", "Test type description");
		dtRepo.save(type);

		// see that type is not null
		assertNotNull(type);

		// test that new type is found in repository
		Iterable<DreamType> types = dtRepo.findAll();
		assertThat(types.toString().contains("Test type"));

		// test that name and last letter equals what we are expecting
		assertEquals("Test type", type.getTypeName());
		assertThat(type.getTypeDescription().endsWith("n"));
	}

	@Test
	public void findDreamTypesTest() {

		// find all types from dreamtype repository
		Iterable<DreamType> types = dtRepo.findAll();
		// test that types list is not null
		assertNotNull(types);

		// test that types list size is eight
		assertThat(types).hasSize(8);
		// check that type id 9 is really empty
		Optional<DreamType> id9 = dtRepo.findById(9L);
		assertThat(id9.isEmpty());

		// test that types contains for example following types
		assertThat(types.toString()).contains("Nightmare", "Daydream", "Recurring dream", "Don't know");
	}

	@Test
	public void deleteDreamTypeTest() {

		// find all types from type repository
		Iterable<DreamType> types = dtRepo.findAll();
		// test that type list is not null
		assertNotNull(types);

		// get some type by id
		Optional<DreamType> typeInDelete = dtRepo.findById(2L);
		// check that id is not null
		assertNotNull(typeInDelete);

		// delete type by id
		dtRepo.deleteById(2L);

		// test that deleted type is not on the list anymore
		Optional<DreamType> typeWasDeleted = dtRepo.findById(2L);
		assertThat(typeWasDeleted.isEmpty());
	}
}
