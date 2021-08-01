package com.manhcode.rest.demo.dto.modelmapper;

import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dto/users")
public class UserModelMapperController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserMmDto findById(@PathVariable Long id) {
        User user =  userService.findById(id).get();

        UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
        
        return userMmDto;
    }

    @GetMapping("/")
    public List<UserMmDto> findAll() {
        List<User> users = userService.findAll();
        List<UserMmDto> dtos = users
                .stream()
                .map(user -> modelMapper.map(user, UserMmDto.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
