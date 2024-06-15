package com.lesson2.Less2.Tabele;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "prava")
public class Authority {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idprava;
    private String imeprava;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;



}
