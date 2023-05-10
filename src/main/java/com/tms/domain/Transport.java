package com.tms.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Transport {
    private int id;
    private String typeTransport;
    private int weightTransport;
    private int volumeTransport;
    private boolean isDeleted;
    private int userId;

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", typeTransport='" + typeTransport + '\'' +
                ", weightTransport=" + weightTransport +
                ", volumeTransport=" + volumeTransport +
                               '}';
    }
}
