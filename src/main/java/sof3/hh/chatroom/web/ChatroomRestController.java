package sof3.hh.chatroom.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import sof3.hh.chatroom.domain.Chatroom;
//import sof3.hh.chatroom.domain.ChatroomRepository;
import sof3.hh.chatroom.domain.Message;
import sof3.hh.chatroom.domain.MessageRepository;



// @RestController
@Controller
public class ChatroomRestController {

    //@Autowired
    //private ChatroomRepository chatroomRepository;
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/messages", method=RequestMethod.GET)
    public @ResponseBody List<Message> messageListRest() {
        return (List<Message>) messageRepository.findAll();
    }

    @RequestMapping(value="/messages/{id}", method=RequestMethod.GET)
    public @ResponseBody Message findMessageRest(@PathVariable("id") Long messageId) {
        return messageRepository.findById(messageId).get();
    }
    
    
    
}
