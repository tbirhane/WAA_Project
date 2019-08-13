package edu.mum.cs.onlinehabeshaclothing.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Email
    private String email;
    @NonNull
    private String password;
    @NonNull




    public Account(Account account) {
//        this.username = account.getUsername();
//        this.password = account.getPassword();
//        this.role = account.getRole();

    }
}
