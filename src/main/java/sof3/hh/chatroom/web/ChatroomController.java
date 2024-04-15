package sof3.hh.chatroom.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import sof3.hh.chatroom.domain.Chatroom;
import sof3.hh.chatroom.domain.ChatroomRepository;
import sof3.hh.chatroom.domain.Message;
import sof3.hh.chatroom.domain.MessageRepository;
import sof3.hh.chatroom.domain.SignupForm;
import sof3.hh.chatroom.domain.User;
import sof3.hh.chatroom.domain.UserRepository;



@Controller
public class ChatroomController {
    
    @Autowired
    private ChatroomRepository chatroomRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    // http://localhost:8080/index
@RequestMapping(value="/index", method=RequestMethod.GET)
    public String getIndex(Model model){
        List<Chatroom> chatrooms = (List<Chatroom>) chatroomRepository.findAll();
        model.addAttribute("chatrooms", chatrooms);
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
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

@RequestMapping(value = "/editchatroom/{id}", method=RequestMethod.GET)
    public String editChatroom(@PathVariable("id") Long chatroomId, Model model) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();
        model.addAttribute("chatroom", chatroom);
        return "editchatroom"; // chatroomform.html
}

@RequestMapping(value = "/editchatroom/{id}", method=RequestMethod.POST)
    public String editedChatroom(@ModelAttribute Chatroom chatroom, @PathVariable("id") Long chatroomId) {
        Chatroom modifiedChatroom = chatroomRepository.findById(chatroomId).get();
        modifiedChatroom.setChatname(chatroom.getChatname());
        chatroomRepository.save(modifiedChatroom);
        return "redirect:/chatroom/" + chatroomId; // chatroomform.html
}

@RequestMapping(value = "/savechatroom", method=RequestMethod.POST)
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

@RequestMapping(value = "/userchoice", method = RequestMethod.GET)
    public String getUserChoice(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User());
        return "userchoice";
    }

@RequestMapping(value = "/useractivities/{id}", method=RequestMethod.GET)
    public String getUserActivities(@PathVariable("id") Long userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        model.addAttribute("user", user.get());
        model.addAttribute("chatrooms", user.get().getChatrooms());
        return "useractivities";
    }

@RequestMapping(value = "/addmessage/{id}", method=RequestMethod.GET)
    public String getAddMessage(@PathVariable("id") Long chatroomId, Model model) {
        model.addAttribute("chatrooms", chatroomRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("message", new Message(null, chatroomRepository.findById(chatroomId).get(), new User()));
        return "messageform"; // messageform.html
    }

@RequestMapping(value = "/savemessage", method=RequestMethod.POST)
    public String saveMessage(@ModelAttribute Message message) {
        Long chatId = message.getChatroom().getId();
        Long userId = message.getUser().getId();
        Chatroom chatroom = chatroomRepository.findById(chatId).get();
        User user = userRepository.findById(userId).get();
        List<User> users = chatroom.getUsers();         //adds user to chatroom.users
        if(!users.contains(user)) {
            users.add(user);
            chatroom.setUsers(users);
        }
        List<Message> chatMessages = chatroom.getMessages();    //adds message to chatroom.messages
        chatMessages.add(message);
        chatroom.setMessages(chatMessages);
        List<Chatroom> chatrooms = user.getChatrooms(); //adds chatroom to user.chatrooms
        if(!chatrooms.contains(chatroom)) {
            chatrooms.add(chatroom);
            user.setChatrooms(chatrooms);
        }
        List<Message> userMessages = user.getMessages();        //adds message to user.messages
        userMessages.add(message);
        user.setMessages(userMessages);
        //chatroomRepository.save(chatroom);
        //userRepository.save(user);
        messageRepository.save(message);
        return "redirect:/chatroom/" + chatId.toString(); // chatroom.html
}

@RequestMapping(value = "/editmessage/{id}", method=RequestMethod.GET)
    public String editMessage(@PathVariable("id") Long messageId, Model model) {
        Message message = messageRepository.findById(messageId).get();
        model.addAttribute("message", message);
        model.addAttribute("chatrooms", chatroomRepository.findAll());
        return "editmessage";
    }

@RequestMapping(value = "editmessage/{id}", method=RequestMethod.POST)
    public String editedMessage(@ModelAttribute Message message, @PathVariable("id") Long messageId) {
        String chatroomId = message.getChatroom().getId().toString();
        Message modifiedMessage = messageRepository.findById(messageId).get();
        modifiedMessage.setMsg(message.getMsg());
        messageRepository.save(modifiedMessage);
        return "redirect:/chatroom/" + chatroomId;
    }

@RequestMapping(value = "/deletemessage/{id}", method=RequestMethod.GET)
    public String deleteMessage(@PathVariable("id") Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        String chatroomId = message.getChatroom().getId().toString();
        //if(message.getUser().getUsername() == UserController.getCurrentUserContext() ){
            messageRepository.deleteById(messageId);
        //}
        return "redirect:/chatroom/" + chatroomId;
    }
    
    // login
@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    // sign-up
@RequestMapping(value = "/signup")
    public String userSignup(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signupform";
    }	
    

@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String password = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPassword = bc.encode(password);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPassword);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (userRepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		userRepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signupform";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signupform";
    		}
    	}
    	else {
    		return "signupform";
    	}
    	return "redirect:/login";    	
    }    
}