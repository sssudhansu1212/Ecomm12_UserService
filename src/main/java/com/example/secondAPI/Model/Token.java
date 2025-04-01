package com.example.secondAPI.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    @ManyToOne
    private User user;
    private String value;
    private Date expiryAt;
}
