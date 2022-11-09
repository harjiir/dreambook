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
			DreamType type1 = new DreamType("Nightmare",
					"Nightmares are fear-inducing dreams and they evoke fear, anxiety, or sadness in the dreamer. This dream state can be the brain’s way of processing life’s daily stress factors, unresolved anxieties or traumas.");
			DreamType type2 = new DreamType("Normal dream",
					"Common dreams about people and experiences we might have forgotten. You accept your dream reality as it is.\n"
							+ "				    These dreams are based on our thoughts and experiences from the day before, sometimes memories from long ago, repressed fears and anxieties, and our strongest desires and urges.");

			DreamType type3 = new DreamType("Daydream",
					"Daydreams occur while you are awake and they may be based on your current situation or on something that you are hoping for in the future.\n"
							+ "					They can be positive or negative, depending on what you are thinking about. Sometimes, daydreams can give you insights into your own thoughts and feelings.");
			DreamType type4 = new DreamType("False-Awakening dream",
					"During a false awakening, the sleeper believes they are awake when in reality they are still sleeping.\n"
							+ "					False awakenings are typically realistic and setting may be identical to real life or there may be small details that are different.");
			DreamType type5 = new DreamType("Lucid dream",
					"Lucid dreaming is when you are aware that you are dreaming. \n"
							+ "					This type of dream can be both exciting and empowering, as it allows you to control the dream. \n"
							+ "					You may find yourself doing things that would be impossible in real life. \n"
							+ "					Lucid dreams can also be used for problem solving or creative purposes, as you can explore different scenarios without any real-world consequences.");
			DreamType type6 = new DreamType("Prophetic dream",
					"Future events occur in your dream before they happen in real life.\n"
							+ "					Prophetic dreams can be found in many different cultures and religions, and they have been a source of fascination for centuries.\n"
							+ "					Prophetic dreams can provide valuable insights into the future, but they should not be considered infallible.");
			DreamType type7 = new DreamType("Recurring dream",
					"Recurring dreams refer to dreams that we repeatedly experience, often with slight variations but containing similar themes.\n"
							+ "					They may be based on something from your past that is unresolved, or they may represent a fear or anxiety that you have. \n"
							+ "					Recurring dreams can reflect unmet needs or areas of frustration that need attention.\n"
							+ "					They can also offer insights into your subconscious mind telling you something about something that you haven’t realized yet.");
			DreamType type8 = new DreamType("Don't know", "You just can't describe your dream");

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
			Keyword kword3 = new Keyword("Desert",
					"In your dreams the desert is a part of you, detached from reality, exploring the vast landscape, being alone and lost. "
							+ "You may be wondering alone in a polar desert, perhaps a cool coastal desert or a cold winter desert. "
							+ "Meanings: Loneliness, Unexplored areas, Stagnant, Lack of feelings or emotions");

			Keyword kword4 = new Keyword("Falling",
					"Extremely common and usually jolt the dreamer out of bed before they hit the ground. "
							+ "Represents feelings of inadequacy or a sense that your life is not stable.");

			Keyword kword5 = new Keyword("Family: The Good",
					"We commonly dream of family eating, gathering, getting hurt, or killed relating to our personal connections and experiences we share. "
							+ "Dreaming about a family reunion suggests a new connection, improved support system, support and love in your life. "
							+ "Watching your siblings in your dreams might make you reflect if you are in need of support or help from them.");
			Keyword kword6 = new Keyword("Family: The Bad",
					"Dreaming of your family dying suggest some sort of transformation or change. "
							+ "Noticing your family hurt might relate to any sort of stressful situation they might be going though. "
							+ "Depending on who did it and where it happened alters the meaning of the dream.");

			Keyword kword7 = new Keyword("Hiding",
					"Powerful symbol representing our unconscious fears and anxieties that we avoid.");

			Keyword kword8 = new Keyword("Ice",
					"Can be seen as a negative symbol reflecting slow or stuck progress in life.");

			Keyword kword9 = new Keyword("Job",
					"It is very common to dream of our work, coworkers or boss. Going in for a job interview in your dream represents a new chance or transition in your life. "
							+ "To dream of losing your job might reflect repressed fears, insecurity or preparing for a real case scenario.");

			Keyword kword10 = new Keyword("Marriage",
					"Represents commitment, harmony, balance or transitions in our life. Also a metaphor for reflecting some type of unity or alliance with someone in your life.");
			Keyword kword11 = new Keyword("Mirror",
					"They connect with inner reflection. A negative omens are often associated with broken or cracked mirrors representing loss or damage.");

			Keyword kword12 = new Keyword("Old Friends",
					"Old friends are commonly known to appear and disappear time to time in our dreams. "
							+ "These dreams don't usually have nothing to do with your old friends and has more to do with ourselves. "
							+ "Dreams of hugging your friend is a very positive symbol that reflects a past connection that has occurred in your life.");

			Keyword kword13 = new Keyword("Parade",
					"Watching a parade in your dream represents something or someone being a distraction in your life. Also the characters or people in the parade may have significance to you. "
							+ "If you are part of the parade then your dream suggests that you are lost and just going with the flow of what everyone around you is doing.");

			Keyword kword14 = new Keyword("War",
					"Usually suggesting inner conflicts that need to be resolved. War takes place in our mind as you are trying to resolve two opposing negative factors. "
							+ "This is why it is quite common to escape from the war in your dream. These conflicts might mirror past unresolved experiences, toxic relationships, work or family environments.");
			kRepo.save(kword1);
			kRepo.save(kword2);
			kRepo.save(kword3);
			kRepo.save(kword4);
			kRepo.save(kword5);
			kRepo.save(kword6);
			kRepo.save(kword7);
			kRepo.save(kword8);
			kRepo.save(kword9);
			kRepo.save(kword10);
			kRepo.save(kword11);
			kRepo.save(kword12);
			kRepo.save(kword13);
			kRepo.save(kword14);

			// LocalDate is an immutable date-time object, often viewed as year-month-day
			// The day must be valid for the year and month,
			// otherwise an exception will be thrown.

			LocalDate date1 = LocalDate.of(2021, 12, 28);
			LocalDate date2 = LocalDate.of(2021, 4, 10);
			LocalDate date3 = LocalDate.of(2021, 7, 4);
			LocalDate date4 = LocalDate.of(2022, 5, 20);

			Dream d1 = new Dream(date1,
					"Warm summer night. A bonfire in the woods and music. Dancing with old friends from comprehensive school, laughter.",
					type2, kword2);
			Dream d2 = new Dream(date2,
					"Parade in Japan. Some kind of market, huge orange fish projected to the air. Floating platform above the street, people cheering and cherry blossoms.",
					type5, kword13);
			Dream d3 = new Dream(date3,
					"Sitting at sofa, christmas tree and it's snowing outside. Mom is baking gingerbreads, brothers playing Guitar Hero",
					type7, kword5);
			Dream d4 = new Dream(date4,
					"Standing in the kitchen when I see an ambulance arrive outside. Father running, telling me to escape through window and never to come back.",
					type1, kword7);
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
