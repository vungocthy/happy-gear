package com.notimplement.happygear.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_shop_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductShopAddress {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "product_shop_address_id")
    private Integer productShopAddressId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_address_id")
    @JsonManagedReference
    private ShopAddress shopAddress;
}
