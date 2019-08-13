package edu.mum.cs.onlinehabeshaclothing.model;

import edu.mum.cs.onlinehabeshaclothing.constraint.FieldMatch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity


public class User {
    @Id
    @GeneratedValue
    private Long id;


    private String firstName;


    private String lastName;

    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @Transient
    private Cart cart;


    private String role;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<CustomerOrder> orders;
}
