package com.manhcode.rest.demo.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.manhcode.rest.demo.dao.UserRepository;
import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.exception.UserExistException;
import com.manhcode.rest.demo.exception.UserNameNotFoundException;
import com.manhcode.rest.demo.exception.UserNotFoundException;
import com.manhcode.rest.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/users")
//    private List<User> findAll() {
//        return userRepository.findAll();
//    }

    @GetMapping("/users")
    @ApiOperation(value = "Find All list of user")
    public List<User> findAll() {
        System.out.println("11111111111111111111");
        if (userService == null) {
            System.out.println("2222222222222222222");
            userService = new UserService();
        }
        return userService.findAll2();
    }

    @PostMapping("/users")
    @ApiOperation(value = "Create a new User")
    public ResponseEntity<Object> saveUser(@ApiParam(value = "User information for a new user to be create") @Valid @RequestBody User user) {
        try {
            User newUser = userService.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newUser.getId()).toUri();

            return ResponseEntity.created(location).build();
        } catch (UserExistException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    //HEATOAS
    @GetMapping("/users/{id}")
    @ApiOperation(value = "Find User by id")
    public EntityModel<User> findById(@PathVariable @Min(1) Long id) {
        try {
            Optional<User> result = userService.findById(id);

            EntityModel<User> resource = EntityModel.of(result.get());

            // HEATOAS get all user
            WebMvcLinkBuilder linkTo =
                    linkTo(methodOn(this.getClass()).findAll());

            resource.add(linkTo.withRel("all-users"));

            //HEATOAS get all orders
            linkTo =
                    linkTo(methodOn(OrderController.class).findAllOrder(id));

            resource.add(linkTo.withRel("all-orders"));

            return resource;
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping ("/users/{id}")
    @ApiOperation(value = "Delete User by id")
    public void deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    @PutMapping ("/users/{id}")
    @ApiOperation(value = "Update User with Id")
    public void updateById(@PathVariable Long id, @RequestBody User user) {
        if (!userService.isExist(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"can't find id: "+id);
        }
        user.setId(id);
        userService.save(user);
    }

    @GetMapping("/users/byusername")
    @ApiOperation(value = "Find User with name")
    public User findByUsername(@RequestParam String name) {
        User user = userService.findByName(name);
        if (user == null) {
            throw new UserNameNotFoundException("can't find name: "+name);
        }
        return user;
    }

//    // Dynamic filter
//    @GetMapping("/users/dynamic/{id}")
//    public MappingJacksonValue findOneDy(@PathVariable Long id) {
//        User user = userService.findById(id).get();
//
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "username","orders");
//
//        FilterProvider filterProvider = new SimpleFilterProvider()
//                .addFilter("UserFilter", filter);
//        MappingJacksonValue mapping = new MappingJacksonValue(user); // co the truyen list
//        mapping.setFilters(filterProvider);
//
//
//        return  mapping;
//    }

    // dynamic with feild from request
    @GetMapping("/users/dynamic/{id}")
    @ApiOperation(value = "Find User Dynamic feild")
    public MappingJacksonValue findOneDyFeild(@PathVariable Long id, @RequestParam Set<String> feilds) {
        User user = userService.findById(id).get();

        System.out.println("--------------------------"+feilds);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(feilds);

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user); // co the truyen list
        mapping.setFilters(filterProvider);


        return  mapping;
    }
}
