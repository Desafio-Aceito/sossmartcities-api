package com.sossmartcities.spring.datajpa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long user_id;

  @Column(name = "user_name", nullable = false)
  private String user_name;

  @Column(name = "user_document", nullable = true)
  private String user_document;

  @Column(name = "birthday", nullable = false)
  private Date birthday;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "social_activity")
  private String social_activity;

  @Column(name = "business_name")
  private String business_name;

  public User() {
  }

  public User(
      String user_name,
      String user_document,
      Date birthday,
      String email,
      String password,
      String social_activity,
      String business_name) {
    this.user_name = user_name;
    this.user_document = user_document;
    this.birthday = birthday;
    this.email = email;
    this.password = password;
    this.social_activity = social_activity;
    this.business_name = business_name;
  }

  public long getUserId() {
    return user_id;
  }

  public void setUserId(long user_id) {
    this.user_id = user_id;
  }

  public String getUserName() {
    return user_name;
  }

  public void setUserName(String user_name) {
    this.user_name = user_name;
  }

  public String getUserDocument() {
    return user_document;
  }

  public void setUserDocument(String user_document) {
    this.user_document = user_document;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSocialActivity() {
    return social_activity;
  }

  public void setSocialActivity(String social_activity) {
    this.social_activity = social_activity;
  }

  public String getBusinessName() {
    return business_name;
  }

  public void setBusinessName(String business_name) {
    this.business_name = business_name;
  }

}
