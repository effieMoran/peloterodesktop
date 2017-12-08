package com.pelotero.mp.bean;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Eli on 8/12/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "combo")
public class Combo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Getter
    @Setter
    private double price;

    @Setter
    @Getter
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @ElementCollection(targetClass=Item.class)
    private Set<Item> items;
}
