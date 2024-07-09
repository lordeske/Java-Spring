package com.legoFigurice.legoFigurice.Domeni;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "figurica")
public class Figurica {

    @Id
    @Column(unique = true , updatable = false)
    @UuidGenerator
    private String idFigurice;
    private String nazivFigurice;
    private String urlSlike;
    private String kategorijaFigurice;
    private String materijalFigurice;


}
