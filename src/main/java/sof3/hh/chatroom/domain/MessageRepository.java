package sof3.hh.chatroom.domain;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

    // peritään findAll(), findBy(), deleteById(), save();
}
