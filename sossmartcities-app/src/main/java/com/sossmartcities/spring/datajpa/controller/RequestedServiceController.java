package com.sossmartcities.spring.datajpa.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sossmartcities.spring.datajpa.model.*;
import com.sossmartcities.spring.datajpa.repository.*;
import com.sossmartcities.spring.datajpa.requests.*;

@RestController
@RequestMapping("/api")
public class RequestedServiceController {

  @Autowired
  RequestedServiceRepository requestedServiceRepository;
  @Autowired
  RequestTrackingRepository requestTrackingRepository;
  @Autowired
  ServiceAddressRepository serviceAddressRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  ServiceKindRepository serviceKindRepository;

  @GetMapping("/requested-services")
  public ResponseEntity<List<RequestedService>> getAllRequestedServices() {
    try {
      List<RequestedService> requestedServices = new ArrayList<>();
      requestedServiceRepository.findAll().forEach(requestedServices::add);

      if (requestedServices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(requestedServices, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/requested-services/{id}")
  public ResponseEntity<RequestedService> getRequestedServiceById(@PathVariable("id") long id) {
    Optional<RequestedService> requestedServiceData = requestedServiceRepository.findById(id);

    if (requestedServiceData.isPresent()) {
      return new ResponseEntity<>(requestedServiceData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  private <T> void log(T arg) {
    System.out.println(arg);
  }

  @PostMapping("/requested-services")
  public ResponseEntity<RequestedService> createRequestedService(@RequestBody RequestedServiceRequest requestBody) {
    try {

      Optional<ServiceKind> _service_kind = serviceKindRepository.findById(requestBody.getServiceKindId());
      Optional<User> _user = userRepository.findById(requestBody.getUserId());

      User user = new User();
      ServiceKind service_kind = new ServiceKind();

      if (!_service_kind.isPresent() || !_user.isPresent()) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }

      ServiceAddress _serviceAddress = requestBody.getServiceAddress();
      ServiceAddress serviceAddress = serviceAddressRepository.save(_serviceAddress);

      RequestTracking _request_tracking = new RequestTracking("pending", "", null);
      RequestTracking request_tracking = requestTrackingRepository.save(_request_tracking);

      RequestedService requestedService = new RequestedService();
      requestedService.setRequestTracking(request_tracking);
      requestedService.setUser(_user.orElse(user));
      requestedService.setServiceName(requestBody.getServiceName());
      requestedService.setServiceKind(_service_kind.orElse(service_kind));
      requestedService.setServiceAddress(serviceAddress);

      RequestedService _requestedService = requestedServiceRepository.save(requestedService);

      return new ResponseEntity<RequestedService>(_requestedService,
          HttpStatus.CREATED);

    } catch (Exception e) {
      this.log("Error creating requested service: " + e.getMessage());
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/requested-services/{id}")
  public ResponseEntity<RequestedService> updateRequestedService(@PathVariable("id") long id,
      @RequestBody RequestedServiceRequest requestBody) {
    Optional<RequestedService> requestedServiceData = requestedServiceRepository.findById(id);

    if (requestedServiceData.isPresent()) {
      RequestedService _requestedService = requestedServiceData.get();

      _requestedService.setServiceName(requestBody.getServiceName());

      RequestedService updatedRequestedService = requestedServiceRepository.save(_requestedService);
      return new ResponseEntity<>(updatedRequestedService, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/requested-services/{id}")
  public ResponseEntity<HttpStatus> deleteRequestedService(@PathVariable("id") long id) {
    try {
      requestedServiceRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      log(e);
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/requested-services")
  public ResponseEntity<HttpStatus> deleteAllRequestedServices() {
    try {
      requestedServiceRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
