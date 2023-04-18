package com.sossmartcities.spring.datajpa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requested_services")
public class RequestedService {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long service_id;

  @ManyToOne
  @JoinColumn(name = "service_kind_id")
  private ServiceKind service_kind;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date created_at;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "service_name")
  private String service_name;

  @OneToOne
  @JoinColumn(name = "protocol")
  private RequestTracking request_tracking;

  @OneToOne(mappedBy = "requested_service")
  private ServiceAddress service_address;

  public RequestedService() {
  }

  public RequestedService(
      ServiceKind service_kind,
      RequestTracking request_tracking,
      String service_name,
      User user,
      ServiceAddress service_address) {
    this.service_kind = service_kind;
    this.request_tracking = request_tracking;
    this.service_name = service_name;
    this.created_at = new Date();
    this.user = user;
  }

  @PrePersist
  protected void onCreate() {
    this.created_at = new Date();
  }

  public long getRequestedServiceId() {
    return this.service_id;
  }

  public void setRequestedServiceId(long service_id) {
    this.service_id = service_id;
  }

  public ServiceAddress getServiceAddress() {
    return service_address;
  }

  public void setServiceAddress(ServiceAddress service_address) {
    this.service_address = service_address;
  }

  public long getServiceId() {
    return service_id;
  }

  public void setServiceId(long service_id) {
    this.service_id = service_id;
  }

  public ServiceKind getServiceKind() {
    return service_kind;
  }

  public void setServiceKind(ServiceKind service_kind) {
    this.service_kind = service_kind;
  }

  public Date getCreatedAt() {
    return created_at;
  }

  public void setCreatedAt(Date created_at) {
    this.created_at = created_at;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getServiceName() {
    return service_name;
  }

  public void setServiceName(String service_name) {
    this.service_name = service_name;
  }

  public RequestTracking getRequestTracking() {
    return request_tracking;
  }

  public void setRequestTracking(RequestTracking request_tracking) {
    this.request_tracking = request_tracking;
  }
}
