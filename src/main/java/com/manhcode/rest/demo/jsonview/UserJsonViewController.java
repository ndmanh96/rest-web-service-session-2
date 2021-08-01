package com.manhcode.rest.demo.jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/jsonview")
public class UserJsonViewController {

    @Autowired
    private UserService userService;

    //external view
    @JsonView(Views.External.class)
    @GetMapping("/external/{id}")
    public User findByIdExternal(@PathVariable @Min(1) Long id) {
        return  userService.findById(id).get();
    }

    //Internal view
    @JsonView(Views.Internal.class)
    @GetMapping("/internal/{id}")
    public User findByIdInternal(@PathVariable @Min(1) Long id) {
        return  userService.findById(id).get();
    }
}
