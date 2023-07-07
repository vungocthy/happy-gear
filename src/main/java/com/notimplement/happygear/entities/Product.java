package com.notimplement.happygear.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_product")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name ="quantity")
	private Integer quantity;
	
	@Column(name = "insurance_info")
	private String insuranceInfo;

	@Column(name = "picture")
	private String picture;
	
	@Column(name = "status")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	@JsonManagedReference
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonManagedReference
	private Category category;
	
	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
	@JoinColumn(name = "product_description_id")
	private ProductDescription productDescription;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Comment> comments;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<ProductPicture> productPictures;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<ShopAddress> shopAddresses;
}
