package com.tms.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_seq")
    @SequenceGenerator(name = "reviews_id_seq", sequenceName = "reviews_id_seq", allocationSize = 1)
    //TODO: under config class
    private int id;

    @Column(name = "reviews")
    private String review;

    @Column(name = "created")
    private Date created;

    @Column(name = "from_which_company")
    private String fromWhichCompanyEmail;

    @Column(name = "about_which_company")
    private int toWhichCompanyId;

    @Column(name = "is_deleted")
    private boolean isDeleted;

}
