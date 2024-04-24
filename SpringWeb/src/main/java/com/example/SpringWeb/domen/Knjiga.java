package com.example.SpringWeb.domen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Knjiga {

    private long isbn;
    private String ime;
    private String autor;
    private String godinaIzdavanja;



}
