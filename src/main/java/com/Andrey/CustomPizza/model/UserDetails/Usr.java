package com.Andrey.CustomPizza.model.UserDetails;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Usr {

    @Id
    String id;
    private String name;
    private String email;
}
