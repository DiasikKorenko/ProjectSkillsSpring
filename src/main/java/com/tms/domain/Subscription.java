package com.tms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Data
@Component
@Entity
@Table(name = "subscription")
/*@NoArgsConstructor
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"user"})*/

    public class Subscription {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_id_seq_gen")
        @SequenceGenerator(name="sub_id_seq_gen", sequenceName = "subscription_id_seq", allocationSize = 1)
        private int id;

        @Column(name = "expire_date")
        private Date expireDate;

        /*@JsonBackReference
        @OneToOne(mappedBy = "subField")
        private User user;*/

}
