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

@SpringBootApplication
public class ChatroomApplication {

	private static final Logger log = LoggerFactory.getLogger(ChatroomApplication.class); // uusi loggerattribuutti

	public static void main(String[] args) {
		SpringApplication.run(ChatroomApplication.class, args);
	}

	@Bean
	public CommandLineRunner ChatDemo(ChatroomRepository chatroomRepository, MessageRepository messageRepository) {
		return (args) -> {
			log.info("save some chatrooms");
			Chatroom chatroom1 = new Chatroom("Politiikka");
			Chatroom chatroom2 = new Chatroom("Talous");

			chatroomRepository.save(chatroom1);
			chatroomRepository.save(chatroom2);

			Message message1 = new Message("Vasen, vasen, vasen.", chatroom1);
			Message message2 = new Message("Eikun oikee!", chatroom1);
			Message message3 = new Message("Riittääkö verot?", chatroom2);
			Message message4 = new Message("Parempia palkkoja varten veroja pitäisi vähentaa.", chatroom2);

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
