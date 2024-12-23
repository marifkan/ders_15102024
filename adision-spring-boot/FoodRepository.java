package com.example.adision;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByCategory(String category);
    Food findByName(String name);
}
