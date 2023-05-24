package com.tms.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favorite_transport")
public class FavoritesTransport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_id_seq")
    @SequenceGenerator(name = "favorite_id_seq", sequenceName = "favorite_id_seq", allocationSize = 1)
    //TODO: under config class
    private int id;

    @Column(name = "id_transport")
    private int transportId;

    @Column(name = "id_user")
    private int userId;

    @Column(name = "user_email", updatable = false)
    private String userEmail;
}
