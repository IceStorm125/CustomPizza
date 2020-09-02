package com.Andrey.CustomPizza.model.UserDetails;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank(message = "Email is required")
    private String email;

    private double discountFactor;

    @Column(nullable = false)
    @Size(min = 3, max = 20,message = "From 3 to 20")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @Size(min = 3,message = "Min 3 symbols")
    @NotBlank(message = "Password is required")
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String activationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;

}
