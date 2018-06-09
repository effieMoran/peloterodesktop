package com.pelotero.mp.bean;

/**
 * Created by User on 11/12/2017.
 */

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "client")
public class Client {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private String cellphoneNumber;

    @Getter
    @Setter
    private String adress;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String cuil;

    @Getter
    @Setter
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate birthDate;

    @Setter
    @Getter
    private String gender;

    @Override
    public String toString() {
        return id +
                " - " + firstName  +
                " " + lastName ;
    }
}
