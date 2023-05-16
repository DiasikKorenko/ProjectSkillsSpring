package com.tms.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "l_user_cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_seq")
    @SequenceGenerator(name="cargo_id_seq", sequenceName = "cargo_id_seq", allocationSize = 1) //TODO: under config class
    private int id;
    @Column(name = "weight_cargo")
    private int weightCargo;
    @Column(name = "width_cargo")
    private int widthCargo;
    @Column(name = "lenght_cargo")
    private int lenghtCargo;
    @Column(name = "hight")
    private int hight;
    @Column(name = "states")
    private String states;
    @Column(name = "route")
    private String route;

    @Column(name = "id_user")
    private int userId;

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", weightCargo=" + weightCargo +
                ", widthCargo=" + widthCargo +
                ", lenghtCargo=" + lenghtCargo +
                ", hight=" + hight +
                ", states='" + states + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
