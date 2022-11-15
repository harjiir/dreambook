package hh.DreamBook;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.DreamBook.web.DreamController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DreamBookApplicationTests {

	// test that the context is creating controller
	@Autowired
	private DreamController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
