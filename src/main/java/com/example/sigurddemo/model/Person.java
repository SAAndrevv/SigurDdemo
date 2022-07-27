package com.example.sigurddemo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Lob
    @Column(name = "CARD", columnDefinition="VARBINARY(16)", unique = true)
    private byte[] card;

    @Column(name = "TYPE")
    private String type;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Guest guest;

}
