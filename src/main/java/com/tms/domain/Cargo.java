package com.tms.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Cargo {
    private int id;
    private int weightCargo;
    private int widthCargo;
    private int lenghtCargo;
    private int hight;
    private String states;
    private String route;
    private boolean isDeleted;
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
