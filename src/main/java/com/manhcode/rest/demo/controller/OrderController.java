package com.manhcode.rest.demo.controller;

import com.manhcode.rest.demo.dao.OrderRepository;
import com.manhcode.rest.demo.entity.Order;
import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.exception.UserNotFoundException;
import com.manhcode.rest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/users/{id}/orders")
    public List<Order> findAllOrder(@PathVariable Long id) {
        try {
            Optional<User> result = userService.findById(id);
            System.out.println(result.get().getOrders());
            return result.get().getOrders();
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping("/users/{id}/orders")
    public ResponseEntity<Object> findAllOrder(@PathVariable Long id, @RequestBody Order order) {
        User newUser = userService.findById(id).get();
        if(newUser == null) {
            throw new UserNotFoundException("can't find id:" +id);
        }

        order.setUser(newUser);
        newUser.addOrder(order);

        orderRepository.save(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
