package ru.intech.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto {
    private Integer id;
    private String from;
    private String text;
    private LocalDateTime dateTime;

    public MessageDto(Integer id, String from, String text, LocalDateTime dateTime) {
        this.id = id;
        this.from = from;
        this.text = text;
        this.dateTime = dateTime;
    }
}
