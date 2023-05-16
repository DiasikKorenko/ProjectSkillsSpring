package com.tms.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "l_user_transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_id_seq")
    @SequenceGenerator(name="transport_id_seq", sequenceName = "transport_id_seq", allocationSize = 1) //TODO: under config class
    private int id;

    @Column(name = "type_transport")
    private String typeTransport;

    @Column(name = "weight_transport")
    private int weightTransport;

    @Column(name = "volume_transport")
    private int volumeTransport;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "id_user")
    private int userId;

   /* @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", typeTransport='" + typeTransport + '\'' +
                ", weightTransport=" + weightTransport +
                ", volumeTransport=" + volumeTransport +
                               '}';
    }*/
}
