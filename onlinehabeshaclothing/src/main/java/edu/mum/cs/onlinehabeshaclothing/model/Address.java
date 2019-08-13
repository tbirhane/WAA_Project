package edu.mum.cs.onlinehabeshaclothing.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty
    private String phone;

    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zip;

}
