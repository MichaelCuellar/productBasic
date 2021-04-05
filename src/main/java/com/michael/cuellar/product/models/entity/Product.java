package com.michael.cuellar.product.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id_product")
    private Long idProduct;
    private String title;
    private Long price;
}
