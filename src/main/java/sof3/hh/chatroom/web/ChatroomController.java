package sof3.hh.chatroom.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sof3.hh.chatroom.domain.Chatroom;

@Controller
public class ChatroomController {
    
    List<Chatroom> chatrooms = new ArrayList<Chatroom>();

    // http://localhost:8080/index
@RequestMapping(value="/index", method=RequestMethod.GET)
    public String getIndex(Model model){

        return "index"; // index.html
    }
    
    // http://localhost:8080/chatroomlist
@RequestMapping(value = "/chatroomlist", method=RequestMethod.GET)
    public String getChatroomlist(Model model){
        chatrooms.add(new Chatroom("Politiikka"));
        chatrooms.add(new Chatroom("Talous"));
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
        chatrooms.add(chatroom);
        return "redirect:/chatroomlist"; // chatroomlist.html
    }

}
