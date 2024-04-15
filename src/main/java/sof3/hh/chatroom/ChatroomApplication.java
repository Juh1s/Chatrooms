package sof3.hh.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sof3.hh.chatroom.domain.Chatroom;
import sof3.hh.chatroom.domain.ChatroomRepository;
import sof3.hh.chatroom.domain.Message;
import sof3.hh.chatroom.domain.MessageRepository;
import sof3.hh.chatroom.domain.User;
import sof3.hh.chatroom.domain.UserRepository;

@SpringBootApplication
public class ChatroomApplication {

	private static final Logger log = LoggerFactory.getLogger(ChatroomApplication.class); // uusi loggerattribuutti

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
	}

	@Bean
	public CommandLineRunner ChatDemo(ChatroomRepository chatroomRepository, UserRepository userRepository, MessageRepository messageRepository) {
		return (args) -> {
			log.info("save some chatrooms");
			Chatroom chatroom1 = new Chatroom("Politiikka");
			Chatroom chatroom2 = new Chatroom("Talous");

			chatroomRepository.save(chatroom1);
			chatroomRepository.save(chatroom2);

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");

			userRepository.save(user1);
			userRepository.save(user2);

			Message message1 = new Message("Vasen, vasen, vasen.", chatroom1, user1);
			Message message2 = new Message("Eikun oikee!", chatroom1, user2);
			Message message3 = new Message("Riittääkö verot?", chatroom2, user2);
			Message message4 = new Message("Parempia palkkoja varten veroja pitäisi vähentaa.", chatroom2, user1);

			messageRepository.save(message1);
			messageRepository.save(message2);
			messageRepository.save(message3);
			messageRepository.save(message4);


			log.info("fetch all chatrooms");
			for (Chatroom chatroom : chatroomRepository.findAll()) {
				log.info(chatroom.toString());
			}
			for (Message message : messageRepository.findAll()) {
				log.info(message.toString());
			}

		};
	}
}
