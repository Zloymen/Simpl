package ru.simple.test.soapservice.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationEntity {

    private Long id;
    private String number;
    private LocalDateTime created;
    private String fio;
    private SystemEntity system;
    private Boolean write;
    private String comment;

    public void persist(){
        this.created = LocalDateTime.now();
    }


}
