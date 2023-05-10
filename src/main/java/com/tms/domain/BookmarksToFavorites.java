package com.tms.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class BookmarksToFavorites {
    private int id;
    private int transportId;
    private int cargoId;
    private int userId;
}
