package edu.t1.dbUser;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="PAYS")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Pays {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="USERID")
    private Integer userId;
    @Column(name="SUMPAY")
    private double sumPay;
    @Column(name="ACCDT")
    private String accDt;
    @Column(name="ACCCT")
    private String accCt;
}
