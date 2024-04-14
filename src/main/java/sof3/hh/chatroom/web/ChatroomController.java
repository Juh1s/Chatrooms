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
//import sof3.hh.chatroom.domain.Message;


@Controller
public class ChatroomController {
    
    @Autowired
    private ChatroomRepository chatroomRepository;

    // http://localhost:8080/index
@RequestMapping(value="/index", method=RequestMethod.GET)
    public String getIndex(Model model){

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
    public String saveChatroom(@ModelAttribute Chatroom chatroom, Model model) {
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


}
