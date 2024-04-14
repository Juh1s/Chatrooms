package sof3.hh.chatroom.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    // konstruktorit
    public Chatroom() {
        this.chatname = null;
    }
    public Chatroom(String chatname) {
        this.chatname = chatname;
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

    // Chatroomin tiedot tekstinä
    @Override
    public String toString() {
        return "Chatroom [id=" + id + ", chatname=" + chatname + "]";
    }
    
}
