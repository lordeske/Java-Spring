package com.example.SpringWeb.domen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Knjiga {

    private long isbn;
    private String ime;
    private String autor;

        @JsonProperty("godina")
        private String godinaIzdavanja;



}
