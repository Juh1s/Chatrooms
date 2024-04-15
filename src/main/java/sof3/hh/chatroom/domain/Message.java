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
    private String msg;

    @ManyToOne
    @JoinColumn(name = "chatroomId")
    private Chatroom chatroom;      // FK

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;              // FK
    
    // konstruktorit
    public Message() {
        this.msg = null;
        this.chatroom = new Chatroom();
        this.user = new User();
    }

    public Message(String msg, Chatroom chatroom, User user) {
        this.msg = msg;
        this.chatroom = chatroom;
        this.user = user;
    }

    // getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", message=" + msg + ", chatroom=" + chatroom + "]";
    }

    
}
