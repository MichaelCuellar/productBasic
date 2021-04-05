package com.michael.cuellar.product.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @Column(name = "id_user")
    private Long idUser;
    private String name;
    private String address;
}
