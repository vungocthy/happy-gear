package com.notimplement.happygear.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_shop_address")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddress {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "shop_address_id")
    private Integer shopAddressId;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @OneToMany(mappedBy = "shopAddress", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ProductShopAddress> productShopAddresses;
}
