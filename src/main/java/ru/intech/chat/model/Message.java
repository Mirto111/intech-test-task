package ru.intech.chat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String text;

    @Column(name = "msg_date_time")
    private LocalDateTime msgDateTime;

    public Message() {
    }

    public Message(Integer id, User user, String text, LocalDateTime msgDateTime) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.msgDateTime = msgDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", msgDateTime=" + msgDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(text, message.text) &&
                Objects.equals(msgDateTime, message.msgDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, msgDateTime);
    }
}
