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

@Data
@Entity
@Table(name = "l_user_cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_seq")
    @SequenceGenerator(name = "cargo_id_seq", sequenceName = "cargo_id_seq", allocationSize = 1)
    private int id;

    @Pattern(regexp = "^[0-9]{1,5}([,.][0-9]+)?$")
    @Column(name = "weight_cargo")
    private String weightCargo;

    @Pattern(regexp = "^[0-9]{1,5}([,.][0-9]+)?$")
    @Column(name = "width_cargo")
    private String widthCargo;

    @Pattern(regexp = "^[0-9]{1,5}([,.][0-9]+)?$")
    @Column(name = "lenght_cargo")
    private String lenghtCargo;

    @Pattern(regexp = "^[0-9]{1,5}([,.][0-9]+)?$")
    @Column(name = "hight")
    private String hight;

    @Column(name = "states")
    private String states;

    @Column(name = "route")
    private String route;

    @Column(name = "id_user", updatable = false)
    private int userId;

    @Column(name = "user_email", updatable = false)
    private String userEmail;
}