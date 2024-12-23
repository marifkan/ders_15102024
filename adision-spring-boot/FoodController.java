package com.example.adision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@CrossOrigin(origins = "http://10.0.2.2")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        Food savedFood = foodRepository.save(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
    }

    @GetMapping
    public List<Food> getFoodsByCategory(@RequestParam("category") String category) {
        return foodRepository.findByCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable int id) {
        foodRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{name}")
    public Food getFoodByName(@PathVariable("name") String name) {
        return foodRepository.findByName(name);
    }
}