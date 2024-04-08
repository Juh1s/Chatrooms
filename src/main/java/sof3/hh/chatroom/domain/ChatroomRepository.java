package sof3.hh.chatroom.domain;

import org.springframework.data.repository.CrudRepository;

public interface ChatroomRepository extends CrudRepository<Chatroom, Long> {
    
    // peritään findAll(), findBy(), deleteById(), save();
}
