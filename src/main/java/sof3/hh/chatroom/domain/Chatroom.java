package sof3.hh.chatroom.domain;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Chatroom {

    // attribuutit
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatroomId")
    private Long id;
    private String chatname;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
    private List<Message> messages;

    @ManyToMany(mappedBy = "chatrooms")
    private List<User> users;

    // konstruktorit
    public Chatroom() {
        super();
        this.chatname = null;
    }
    public Chatroom(String chatname) {
        super();
        this.chatname = chatname;
    }

    // lisää viesti
    public void addMessage(Message newMessage) {
        List<Message> oldMessages = this.getMessages();
        oldMessages.add(newMessage);
        setMessages(oldMessages);
    }

    // setterit ja getterit
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getChatname() {
        return chatname;
    }
    public void setChatname(String chatname) {
        this.chatname = chatname;
    }
    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    

    // Chatroomin tiedot tekstinä
    @Override
    public String toString() {
        return "Chatroom [id=" + id + ", chatname=" + chatname + "]";
    }
    
}
