package com.pelotero.mp.bean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Eli on 8/12/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "combo")
public class Combo {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private String name;


    @Setter
    @Getter
    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @ElementCollection(targetClass=Item.class)
    private List<Item> items;

    @Override
    public String toString() {
        return  id +
                " - $" + price +
                " - " + name ;
    }
}
