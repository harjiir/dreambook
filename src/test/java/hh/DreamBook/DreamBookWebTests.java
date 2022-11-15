package hh.DreamBook;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

// Test will start the full Spring application context, but without the server by using Spring’s MockMvc.
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DreamBookWebTests {

	@Autowired
	private MockMvc mockMvc;
}
