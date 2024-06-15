package com.lesson2.Less2.Tabele;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Table(name = "korisnici")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String lozinka;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "korisnici_prava", joinColumns = @JoinColumn(name = "idkorisnika"),
    inverseJoinColumns = @JoinColumn(name = "idpravaa"))
    private Set<Authority> authorities;


}
