package com.notimplement.happygear.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notimplement.happygear.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	List<Category> findByStatus(Boolean status);
}
