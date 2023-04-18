package com.sossmartcities.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sossmartcities.spring.datajpa.model.User;
import com.sossmartcities.spring.datajpa.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
    try {
      List<User> users = new ArrayList<User>();

      if (name == null) {
        userRepository.findAll().forEach(users::add);
      }

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      return new ResponseEntity<User>(userData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      System.out.println(user);
      User _user = userRepository.save(
          new User(
              user.getUserName(),
              user.getUserDocument(),
              user.getBirthday(),
              user.getEmail(),
              user.getPassword(),
              user.getSocialActivity(),
              user.getBusinessName()));
      return new ResponseEntity<User>(_user, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      User _user = userData.get();
      _user.setUserName(user.getUserName());
      _user.setUserDocument(user.getUserDocument());
      _user.setBirthday(user.getBirthday());
      _user.setEmail(user.getEmail());
      _user.setPassword(user.getPassword());
      _user.setSocialActivity(user.getSocialActivity());
      _user.setBusinessName(user.getBusinessName());
      return new ResponseEntity<User>(userRepository.save(_user), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
    try {
      userRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/users")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    try {
      userRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
