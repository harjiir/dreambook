package hh.DreamBook;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.DreamBook.domain.Dream;
import hh.DreamBook.domain.DreamRepo;
import hh.DreamBook.domain.DreamType;
import hh.DreamBook.domain.DreamTypeRepo;
import hh.DreamBook.domain.Keyword;
import hh.DreamBook.domain.KeywordRepo;
import hh.DreamBook.domain.User;
import hh.DreamBook.domain.UserRepo;

@SpringBootApplication
public class DreamBookApplication {

	// new logger attribute
	private static final Logger log = LoggerFactory.getLogger(DreamBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DreamBookApplication.class, args);
	}

	// Test data for H2- testdatabase everytime it's started
	@Bean
	public CommandLineRunner DreamBookDemo(DreamRepo dRepo, DreamTypeRepo dtRepo, KeywordRepo kRepo, UserRepo uRepo) {
		return (args) -> {
			log.info("Saving test data: ");

			// some Dream Types
			DreamType type1 = new DreamType("Nightmare");
			DreamType type2 = new DreamType("Normal dream");
			DreamType type3 = new DreamType("Day dream");
			DreamType type4 = new DreamType("False-Awakening dream");
			DreamType type5 = new DreamType("Lucid dream");
			DreamType type6 = new DreamType("Prophetic dream");
			DreamType type7 = new DreamType("Recurring dream");
			DreamType type8 = new DreamType("Don't know");
			dtRepo.save(type1);
			dtRepo.save(type2);
			dtRepo.save(type3);
			dtRepo.save(type4);
			dtRepo.save(type5);
			dtRepo.save(type6);
			dtRepo.save(type7);
			dtRepo.save(type8);

			// some Keywords
			Keyword kword1 = new Keyword("Beach",
					"Could suggest a need for vacation. Waves are often depictions of emotional disturbances approaching. Sand showing the passing of the time, a symbol to explore the unknown before it’s too late.");
			Keyword kword2 = new Keyword("Dancing",
					"Could signify confidence, cheer, merry-making and general happiness as well as be symbolic of a cry for freedom, possible trust issues in your inner circle, and a battle with anxiety.");
			Keyword kword3 = new Keyword("Hiding",
					"Powerful symbol representing our unconscious fears and anxieties that we avoid.");
			Keyword kword4 = new Keyword("Ice",
					"Can be seen as a negative symbol reflecting slow or stuck progress in life.");
			Keyword kword5 = new Keyword("Marriage",
					"Represents commitment, harmony, balance or transitions in our life. Also a metaphor for reflecting some type of unity or alliance with someone in your life.");
			kRepo.save(kword1);
			kRepo.save(kword2);
			kRepo.save(kword3);
			kRepo.save(kword4);
			kRepo.save(kword5);

			// LocalDate is an immutable date-time object, often viewed as year-month-day
			// The day must be valid for the year and month,
			// otherwise an exception will be thrown.
			LocalDate date1 = LocalDate.of(2020, 2, 10);
			LocalDate date2 = LocalDate.of(2021, 12, 11);
			LocalDate date3 = LocalDate.of(2021, 5, 30);
			LocalDate date4 = LocalDate.of(2022, 6, 14);

			// some Dreams
			Dream d1 = new Dream(date1,
					"Ambulance arrives to our yard, father says to escape through kitchen window and hide somewhere.",
					type1, kword3);
			Dream d2 = new Dream(date2, "Skating with mom at Pirkkola.", type2, kword4);
			Dream d3 = new Dream(date3,
					"Dancing with comprehensive school friends in the woods, bonfire somewhere near.", type2, kword2);
			Dream d4 = new Dream(date4, "Standing at the shore in Latvia, recognized it from our summer car trips.",
					type7, kword1);
			dRepo.save(d1);
			dRepo.save(d2);
			dRepo.save(d3);
			dRepo.save(d4);

			// Some new users
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			uRepo.save(user1);
			uRepo.save(user2);
		};
	}

}