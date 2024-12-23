package com.example.adision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://10.0.2.2")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public Order getOrderById(@RequestParam int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {

        Optional<Order> existingOrder = orderRepository.findById(id);
        if (!existingOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Order updatedOrder = existingOrder.get();
        updatedOrder.setFoods(order.getFoods());

        Order savedOrder = orderRepository.save(updatedOrder);
        return ResponseEntity.ok(savedOrder);
    }
}
