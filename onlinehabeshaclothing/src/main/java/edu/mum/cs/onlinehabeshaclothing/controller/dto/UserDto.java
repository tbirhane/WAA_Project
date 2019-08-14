package edu.mum.cs.onlinehabeshaclothing.controller.dto;


import edu.mum.cs.onlinehabeshaclothing.constraint.FieldMatch;
import edu.mum.cs.onlinehabeshaclothing.model.Address;
import edu.mum.cs.onlinehabeshaclothing.model.Cart;
import edu.mum.cs.onlinehabeshaclothing.model.CustomerOrder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor



@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Email
    private String confirmEmail;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;


    @Valid
    private Address address;

    @NotNull
    private String role;

    private List<CustomerOrder> orders;

}
