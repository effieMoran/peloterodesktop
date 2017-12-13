package com.pelotero.mp.bean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.List;

/**
 * Created by User on 13/12/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    private double total;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "client_id")
    private Client client;

    /*
    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @ElementCollection(targetClass = BillLine.class)
    private List<BillLine> billLines;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @ElementCollection(targetClass=Payment.class)
    private List<Payment> payments;
*/
}
