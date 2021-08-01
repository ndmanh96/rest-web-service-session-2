package com.manhcode.rest.demo.service;

import com.manhcode.rest.demo.dao.UserRepository;
import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.exception.UserExistException;
import com.manhcode.rest.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAll2() {
        return userRepository.findAll();
    }

    public User save(User user) throws UserNotFoundException  {
        //check username
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserExistException("Already Exist User");
        }
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) throws UserNotFoundException {
        Optional<User> rs = userRepository.findById(id);
        if (!rs.isPresent()) {
            throw new UserNotFoundException("Can't find user with id: "+id);
        }
        return rs;
    }

    public boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    public void deleteById(Long id) throws UserNotFoundException{
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Can't find user with id: "+id);
        }
        userRepository.deleteById(id);
    }

    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }
}
