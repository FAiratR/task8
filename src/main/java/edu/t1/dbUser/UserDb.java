package edu.t1.dbUser;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="USERS")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserDb {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="USERNAME")
    private String userName;
    @Column(name="SUMLIMIT")
    private double sumLimit;
}
