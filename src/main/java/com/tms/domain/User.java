package com.tms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.sql.Date;


@Data
@Entity
@Table(name = "users")
/*@ToString(exclude = {"subField"})
@EqualsAndHashCode(exclude = {"subField"})*/
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "users_id_seq", allocationSize = 1) //TODO: under config class
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "unp_or_tin")
    private String unpTin;

    @Column(name = "countries")
    private String countries;

    @Pattern(regexp = "^(\\+)?((\\d{2,3}) ?\\d|\\d)(([ -]?\\d)|( ?(\\d{2,3}) ?)){5,12}\\d$",
            message = "Не соответствие формату номера телефона")
    @Column(name = "telephone_1")
    private String telephone1;
    @Column(name = "telephone_2")
    private String telephone2;
    @Column(name = "telephone_3")
    private String telephone3;


    @Column(name = "created")
    private Date created;
    @Column(name = "changed")
    private Date changed;
    @Column(name = "is_deleted")
    private boolean isDeleted;


   /* @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_id", referencedColumnName = "id")
    private Subscription subField;*/


}
