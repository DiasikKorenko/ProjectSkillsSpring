package com.tms.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "favorite_cargo")
public class FavoritesCargo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_cargo_id_seq")
    @SequenceGenerator(name = "favorite_cargo_id_seq", sequenceName = "favorite_cargo_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "id_cargo")
    private int cargoId;

    @Column(name = "id_user")
    private int userId;

    @Column(name = "user_email", updatable = false)
    private String userEmail;
}