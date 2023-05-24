package com.tms.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "l_user_transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_id_seq")
    @SequenceGenerator(name = "transport_id_seq", sequenceName = "transport_id_seq", allocationSize = 1)
    //TODO: under config class
    private int id;

    @Column(name = "type_transport")
    private String typeTransport;

    @Pattern(regexp = "^\\d{1,5}(\\.\\d{1,5})?$")
    @Column(name = "weight_transport")
    private String weightTransport;

    @Pattern(regexp = "^\\d{1,5}(\\.\\d{1,5})?$")
    @Column(name = "volume_transport")
    private String volumeTransport;

    @Column(name = "id_user", updatable = false)
    private int userId;

    @Column(name = "user_email", updatable = false)
    private String userEmail;


}
