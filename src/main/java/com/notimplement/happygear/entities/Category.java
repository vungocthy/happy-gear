package com.notimplement.happygear.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    
    @Column(name = "category_name")
    private String categoryName;

    @Column (name = "category_picture")
    private String categoryPicture;
    
    @Column(name = "status")
    private Boolean status;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Product> products;
}
