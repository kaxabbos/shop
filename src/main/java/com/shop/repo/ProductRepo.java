package com.shop.repo;

import com.shop.model.Product;
import com.shop.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByCategoryAndNameContainingOrderByPriceAsc(Category category, String name);
    List<Product> findAllByCategoryAndNameContainingOrderByPriceDesc(Category category, String name);
}
