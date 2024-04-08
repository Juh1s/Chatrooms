package sof3.hh.chatroom.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chatroom {

    // attribuutit
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String chatname;

    // konstruktorit
    public Chatroom() {
        super();
        this.id = null;
        this.chatname = null;
    }
    public Chatroom(String chatname) {
        super();
        this.id = null;
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

    // Chatroomin tiedot tekstinä
    @Override
    public String toString() {
        return "Chatroom [id=" + id + ", chatname=" + chatname + "]";
    }

    
}
