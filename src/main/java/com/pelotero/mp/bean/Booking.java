package com.pelotero.mp.bean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by User on 12/12/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Setter
    @Getter
    private String kidName;

    @Getter
    @Setter
    private int kidAge;

    @Getter
    @Setter
    private int kidsInvited;

    @Getter
    @Setter
    private String kidGender;

    @Getter
    @Setter
    private String turn;

    @Getter
    @Setter
    private LocalDate date;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "duty_id")
    private Duty duty;

    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @Setter
    @Getter
    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "party_id")
    private Party party;


    @Setter
    @Getter
    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "client_id")
    private Client client;

}