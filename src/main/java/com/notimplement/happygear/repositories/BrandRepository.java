package com.notimplement.happygear.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notimplement.happygear.entities.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	List<Brand> findByStatus(Boolean status);
}
