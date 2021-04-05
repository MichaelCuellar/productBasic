package com.michael.cuellar.product.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Long idSale;
    private Calendar date;
    @ManyToOne ()
    @JoinColumn(name = "FK_USER", nullable = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sale")
    private List<Detail> detailList;
    @Column(name = "total_cost")
    private double totalCost;
}
