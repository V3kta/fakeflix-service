package de.v3.fakeflix.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
