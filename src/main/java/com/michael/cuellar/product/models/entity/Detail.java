package com.michael.cuellar.product.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Long idDetail;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_SALE", nullable = false)
    private Sale sale;
    @ManyToOne()
    private Product product;
}
