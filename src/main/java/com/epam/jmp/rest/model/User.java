package com.epam.jmp.rest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private Long id;
    private String name;
    private String surname;
    private String email;

}
