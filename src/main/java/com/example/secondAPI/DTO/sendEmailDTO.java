package com.example.secondAPI.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class sendEmailDTO {
    private String from;
    private String to;
    private String subject;
    private String body;
}
