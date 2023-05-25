package com.tms.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;

    @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Zа-яА-Я]+(?:[\\s-][a-zA-Zа-яА-Я]+)*$")
    @Column(name = "first_name")
    private String firstName;

    @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Zа-яА-Я]+(?:[\\s-][a-zA-Zа-яА-Я]+)*$")
    @Column(name = "user_name")
    private String userName;

    @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Zа-яА-Я]+(?:[\\s-][a-zA-Zа-яА-Я]+)*$")
    @Column(name = "last_name")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@(mail\\.ru|gmail\\.com)$")
    @Column(name = "email")
    private String email;

    @Size(min = 2, max = 100)
    @Column(name = "password_user", updatable = false)
    private String passwordUser;

    @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Zа-яА-Я]+(?:[\\s-][a-zA-Zа-яА-Я]+)*$")
    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "legal_address")
    private String legalAddress;

    @Pattern(regexp = "^\\d{9}$")
    @Column(name = "unp_or_tin")
    private String unpTin;

    @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Zа-яА-Я]+(?:[\\s-][a-zA-Zа-яА-Я]+)*$")
    @Column(name = "countries")
    private String countries;

    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    @Column(name = "telephone_1")
    private String telephone1;

    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    @Column(name = "telephone_2")
    private String telephone2;

    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    @Column(name = "telephone_3")
    private String telephone3;

    @Column(name = "created", updatable = false)
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "role", updatable = false)
    private String role;
}