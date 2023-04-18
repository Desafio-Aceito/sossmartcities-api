package com.sossmartcities.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "service_address")
public class ServiceAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long service_address_id;

  @Column(name = "street_name")
  private String street_name;

  @Column(name = "state")
  private String state;

  @Column(name = "city")
  private String city;

  @Column(name = "zip_code")
  private Number zip_code;

  @OneToOne
  @JoinColumn(name = "service_id")
  private RequestedService requested_service;

  public ServiceAddress() {
  }

  public ServiceAddress(
      String street_name,
      String state,
      String city,
      Number zip_code,
      RequestedService requested_service) {
    this.street_name = street_name;
    this.state = state;
    this.city = city;
    this.zip_code = zip_code;
    this.requested_service = requested_service;
  }

  public long getServiceAddressId() {
    return this.service_address_id;
  }

  public void setServiceAddressId(long service_address_id) {
    this.service_address_id = service_address_id;
  }

  public String getStreetName() {
    return street_name;
  }

  public void setStreetName(String street_name) {
    this.street_name = street_name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Number getZipCode() {
    return zip_code;
  }

  public void setZipCode(Number zip_code) {
    this.zip_code = zip_code;
  }

  public RequestedService getRequestedService() {
    return requested_service;
  }

  public void setRequestedService(RequestedService requested_service) {
    this.requested_service = requested_service;
  }
}
