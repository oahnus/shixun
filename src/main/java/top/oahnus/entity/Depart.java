package top.oahnus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oahnus on 2017/5/28
 * 17:08.
 */
@Data
@Entity(name = "depart")
public class Depart {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
