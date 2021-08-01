package com.manhcode.rest.demo.dto.mapstruct;

import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dtoms/users")
public class DtoMapStructController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserMsDto findById(@PathVariable Long id) {
        User user = userService.findById(id).get();
        return userMapper.userToUserDto(user);

    }

    @GetMapping("/")
    public List<UserMsDto> findAll() {
        return userMapper.usersToUserDtos(userService.findAll());
    }
}
