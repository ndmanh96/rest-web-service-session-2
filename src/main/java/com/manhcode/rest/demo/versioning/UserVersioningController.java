package com.manhcode.rest.demo.versioning;

import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version/users")
public class UserVersioningController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

//    Use different URI
    @GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
    public UserDtoV1 findByIdV1(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

        return userDtoV1;
    }

    @GetMapping({"/v2.0/{id}", "/v2.1/{id}"})
    public UserDtoV2 findByIdV2(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

        return userDtoV2;
    }

//    Use Param
    @GetMapping(value = "/{id}", params = "version=1")
    public UserDtoV1 findByIdV1Param(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

        return userDtoV1;
    }

    @GetMapping(value = "/{id}", params = "version=2")
    public UserDtoV2 findByIdV2Param(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

        return userDtoV2;
    }

    // Use Header
    @GetMapping(value = "/{id}",headers = "X-VERSION=1")
    public UserDtoV1 findByIdV1Header(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

        return userDtoV1;
    }

    @GetMapping(value = "/{id}",headers = "X-VERSION=2")
    public UserDtoV2 findByIdV2Header(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

        return userDtoV2;
    }

    // Custom by produce

    @GetMapping(value = "/{id}",produces = "application/vnd.company.app-v1+json")
    public UserDtoV1 findByIdV1Produce(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

        return userDtoV1;
    }

    @GetMapping(value = "/{id}",produces = "application/vnd.company.app-v2+json")
    public UserDtoV2 findByIdV2Produce(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

        return userDtoV2;
    }
}
