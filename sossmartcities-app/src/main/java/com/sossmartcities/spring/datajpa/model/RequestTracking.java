package com.sossmartcities.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "request_tracking")
public class RequestTracking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long protocol;

  @OneToOne
  @JoinColumn(name = "service_id")
  private RequestedService requested_service;

  @Column(name = "status")
  private String status;

  @Column(name = "request_doubts")
  private String request_doubts;

  public RequestTracking() {
  }

  public RequestTracking(
      String status,
      String request_doubts,
      RequestedService requested_service) {
    this.requested_service = requested_service;
    this.status = status;
    this.request_doubts = request_doubts;
  }

  public long getRequestTrackingProtocol() {
    return this.protocol;
  }

  public void setRequestProtocol(long protocol) {
    this.protocol = protocol;
  }

  public RequestedService getRequested_service() {
    return requested_service;
  }

  public void setRequested_service(RequestedService requested_service) {
    this.requested_service = requested_service;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRequest_doubts() {
    return request_doubts;
  }

  public void setRequest_doubts(String request_doubts) {
    this.request_doubts = request_doubts;
  }
}
