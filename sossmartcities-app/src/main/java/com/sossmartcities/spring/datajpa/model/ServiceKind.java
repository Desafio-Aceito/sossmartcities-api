package com.sossmartcities.spring.datajpa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service_kind")
public class ServiceKind {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long service_kind_id;

  @Column(name = "service_kind_name", nullable = true)
  private String service_kind_name;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date created_at;

  public ServiceKind() {
  }

  public ServiceKind(String service_kind_name) {
    this.service_kind_name = service_kind_name;
  }

  public long getServiceKindId() {
    return this.service_kind_id;
  }

  public void setServiceKindId(long service_kind_id) {
    this.service_kind_id = service_kind_id;
  }

  public String getServiceKindName() {
    return this.service_kind_name;
  }

  public void setServiceKindName(String service_kind_name) {
    this.service_kind_name = service_kind_name;
  }

  public Date getCreatedAt() {
    return this.created_at;
  }

  public void setCreatedAt(Date created_at) {
    this.created_at = created_at;
  }

  @PrePersist
  protected void onCreate() {
    this.created_at = new Date();
  }

}
