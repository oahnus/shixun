package top.oahnus.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by oahnus on 2017/5/28
 * 17:09.
 */
@Data
@Entity(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name="depart_id")
    private Depart depart;
}
