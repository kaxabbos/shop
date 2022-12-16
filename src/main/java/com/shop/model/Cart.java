package com.shop.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private int quantity;
    private Long user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Cart(int quantity, Long user) {
        this.quantity = quantity;
        this.user = user;
    }
}
