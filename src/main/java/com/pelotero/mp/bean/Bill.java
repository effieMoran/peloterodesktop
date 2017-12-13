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

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import java.util.Set;

/**
 * Created by User on 13/12/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @Setter
    private double total = 0;

    @Setter
    @Getter
    private double totalPayed = 0;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "client_id")
    private Client client;

    @Setter
    @Getter
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
    @ElementCollection(targetClass = Payment.class)
    private List<Payment> payments;

    @Setter
    @Getter
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
    @ElementCollection(targetClass = BillLine.class)
    private Set<BillLine> billLines;

    @Override
    public String toString() {
        return "Total: $" + total +"\n" +
                "Detalle: \n" + billLines ;
    }
}
