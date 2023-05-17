package com.tms.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@Entity
@Table(name = "l_user_cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_seq")
    @SequenceGenerator(name = "cargo_id_seq", sequenceName = "cargo_id_seq", allocationSize = 1)
    //TODO: under config class
    private int id;
    @Pattern(regexp = "^([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$",
            message = "Не соответствие формату weightCargo ")
    @Column(name = "weight_cargo")
    private int weightCargo;

    @Pattern(regexp = "^([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$",
            message = "Не соответствие формату widthCargo ")
    @Column(name = "width_cargo")
    private int widthCargo;

    @Pattern(regexp = "^([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$",
            message = "Не соответствие формату lenghtCargo ")
    @Column(name = "lenght_cargo")
    private int lenghtCargo;

    @Pattern(regexp = "^([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$",
            message = "Не соответствие формату hight ")
    @Column(name = "hight")
    private int hight;

    @Column(name = "states")
    private String states;
    @Column(name = "route")
    private String route;

    @Column(name = "id_user")
    private int userId;


}
