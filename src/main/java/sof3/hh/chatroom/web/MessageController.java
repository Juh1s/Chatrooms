package sof3.hh.chatroom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/*import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
*/
import sof3.hh.chatroom.domain.MessageRepository;


@Controller
public class MessageController {

    @Autowired MessageRepository messageRepository;

}
