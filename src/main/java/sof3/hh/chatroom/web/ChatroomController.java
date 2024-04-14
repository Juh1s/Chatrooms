package sof3.hh.chatroom.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sof3.hh.chatroom.domain.Chatroom;
import sof3.hh.chatroom.domain.ChatroomRepository;
import sof3.hh.chatroom.domain.Message;
import sof3.hh.chatroom.domain.MessageRepository;


@Controller
public class ChatroomController {
    
    @Autowired
    private ChatroomRepository chatroomRepository;
    @Autowired
    private MessageRepository messageRepository;

    // http://localhost:8080/index
@RequestMapping(value="/index", method=RequestMethod.GET)
    public String getIndex(Model model){
        List<Chatroom> chatrooms = (List<Chatroom>) chatroomRepository.findAll();
        model.addAttribute("chatrooms", chatrooms);
        List<Message> messages = (List<Message>) messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "index"; // index.html
}
    
    // http://localhost:8080/chatroomlist
@RequestMapping(value = "/chatroomlist", method=RequestMethod.GET)
    public String getChatroomlist(Model model){
        List<Chatroom> chatrooms = (List<Chatroom>) chatroomRepository.findAll();
        model.addAttribute("chatrooms", chatrooms);

        return "chatroomlist"; // chatroomlist.html
}

@RequestMapping(value = "/addchatroom", method=RequestMethod.GET)
    public String getAddChatroom(Model model) {
        model.addAttribute("chatroom", new Chatroom());
        return "chatroomform"; // chatroomform.html
}

@RequestMapping(value = "/addchatroom", method=RequestMethod.POST)
    public String saveChatroom(@ModelAttribute Chatroom chatroom) {
        chatroomRepository.save(chatroom);
        return "redirect:/chatroomlist"; // chatroomlist.html
}

@RequestMapping(value = "/deletechatroom/{id}", method=RequestMethod.GET)
    public String deleteChatroom(@PathVariable("id") Long chatroomId) {
        chatroomRepository.deleteById(chatroomId);
        return "redirect:/chatroomlist";
}

@RequestMapping(value = "/chatroom/{id}", method=RequestMethod.GET)
    public String getChatroom(@PathVariable("id") Long chatroomId, Model model) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();
        model.addAttribute("chatroom", chatroom);
        return "chatroom";
}
    
@RequestMapping(value = "/addmessage/{id}", method=RequestMethod.GET)
public String getAddMessate(@PathVariable("id") Long chatroomId, Model model) {
    //Chatroom chatroom = chatroomRepository.findById(chatroomId);
    model.addAttribute("chatrooms", chatroomRepository.findAll());
    model.addAttribute("message", new Message(null, chatroomRepository.findById(chatroomId).get()));
    return "messageform"; // messageform.html
}

@RequestMapping(value = "/addmessage", method=RequestMethod.POST)
    public String saveMessage(@ModelAttribute Message message) {
        String chatroomId = message.getChatroom().getId().toString();
        messageRepository.save(message);
        return "redirect:/chatroom/" + chatroomId; // chatroom.html
}

@RequestMapping(value = "/deletemessage/{id}", method=RequestMethod.GET)
    public String deleteMessage(@PathVariable("id") Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        String chatroomId = message.getChatroom().getId().toString();
        messageRepository.deleteById(messageId);
        return "redirect:/chatroom/" + chatroomId;
}
}