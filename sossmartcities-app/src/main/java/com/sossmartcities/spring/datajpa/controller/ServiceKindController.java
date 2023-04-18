package com.sossmartcities.spring.datajpa.controller;

import com.sossmartcities.spring.datajpa.model.ServiceKind;
import com.sossmartcities.spring.datajpa.repository.ServiceKindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ServiceKindController {

  @Autowired
  ServiceKindRepository serviceKindRepository;

  @GetMapping("/service-kinds")
  public ResponseEntity<List<ServiceKind>> list() {
    try {
      List<ServiceKind> services = new ArrayList<ServiceKind>();

      serviceKindRepository.findAll().forEach(services::add);

      if (services.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<List<ServiceKind>>(services, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/service-kinds/{id}")
  public ResponseEntity<ServiceKind> getServiceById(@PathVariable("id") long id) {
    Optional<ServiceKind> serviceData = serviceKindRepository.findById(id);
    if (serviceData.isPresent()) {
      return new ResponseEntity<>(serviceData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/service-kinds")
  public ResponseEntity<ServiceKind> add(@RequestBody ServiceKind serviceKind) {
    System.out.println(serviceKind);
    try {
      ServiceKind _serviceKind = serviceKindRepository.save(new ServiceKind(serviceKind.getServiceKindName()));
      return new ResponseEntity<ServiceKind>(_serviceKind, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/service-kinds/{id}")
  public ResponseEntity<ServiceKind> update(@RequestBody ServiceKind serviceKind, @PathVariable long id) {
    Optional<ServiceKind> serviceData = serviceKindRepository.findById(id);

    if (serviceData.isPresent()) {
      ServiceKind _serviceKind = serviceData.get();
      _serviceKind.setServiceKindName(serviceKind.getServiceKindName());
      serviceKindRepository.save(_serviceKind);
      return new ResponseEntity<ServiceKind>(_serviceKind, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/service-kinds/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
    try {
      Optional<ServiceKind> serviceKind = serviceKindRepository.findById(id);
      if (serviceKind.isPresent()) {
        serviceKindRepository.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
      }
      return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/service-kinds")
  public ResponseEntity<HttpStatus> deleteAll() {
    try {
      serviceKindRepository.deleteAll();
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
