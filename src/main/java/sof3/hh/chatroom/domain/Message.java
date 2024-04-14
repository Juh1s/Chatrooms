package sof3.hh.chatroom.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

    // attribuutit
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="messageId")
    private Long id;
    private String message;

    @ManyToOne
    @JoinColumn(name = "chatroomId")
    private Chatroom chatroom;      // FK
    
    // konstruktorit
    public Message() {
        this.message = null;
        this.chatroom = new Chatroom();
    }

    public Message(String message, Chatroom chatroom) {
        this.message = message;
        this.chatroom = chatroom;
    }

    // getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", message=" + message + ", chatroom=" + chatroom + "]";
    }
    
}
