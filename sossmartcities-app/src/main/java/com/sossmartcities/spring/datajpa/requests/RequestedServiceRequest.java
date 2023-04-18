package com.sossmartcities.spring.datajpa.requests;

import com.sossmartcities.spring.datajpa.model.ServiceAddress;

public class RequestedServiceRequest {
  private long service_kind_id;
  private String service_name;
  private long user_id;
  private ServiceAddress service_address;

  public long getServiceKindId() {
    return service_kind_id;
  }

  public void setServiceKindId(long service_kind_id) {
    this.service_kind_id = service_kind_id;
  }

  public String getServiceName() {
    return service_name;
  }

  public void setServiceName(String service_name) {
    this.service_name = service_name;
  }

  public long getUserId() {
    return user_id;
  }

  public void setUserId(long user_id) {
    this.user_id = user_id;
  }

  public ServiceAddress getServiceAddress() {
    return this.service_address;
  }

  public void setServiceAddress(ServiceAddress service_address) {
    this.service_address = service_address;
  }
}
