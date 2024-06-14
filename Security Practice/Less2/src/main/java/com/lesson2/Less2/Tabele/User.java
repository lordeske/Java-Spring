package com.lesson2.Less2.Tabele;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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


}
