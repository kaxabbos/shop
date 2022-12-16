package com.shop.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Warehouse {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String address;
    private String description;
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Warehouse(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setWarehouse(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setWarehouse(null);
    }
}
