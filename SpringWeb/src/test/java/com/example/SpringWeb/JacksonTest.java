package com.example.SpringWeb;

import com.example.SpringWeb.domen.Knjiga;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {


    @Test
    public void ObjekatMozeDaKreiraJsonIzObjekta () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Knjiga knjiga = Knjiga.builder()
                .isbn(123-345-34242432)
                .ime("Mali mrav")
                .autor("Slobodan Misic")
                .godinaIzdavanja("2020")
                .build();

        String rezultat =   objectMapper.writeValueAsString(knjiga);
        assertThat(rezultat).isEqualTo("{\"isbn\":-34242654,\"ime\":\"Mali mrav\",\"autor\":\"Slobodan Misic\",\"godina\":\"2020\"}");


    }


    @Test
    public void JSONuJavu () throws JsonProcessingException {
        Knjiga knjiga1 = Knjiga.builder()
                .isbn(123-345-34242432)
                .ime("Mali mrav")
                .autor("Slobodan Misic")
                .godinaIzdavanja("2020")
                .build();


        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = "{\"isbn\":-34242654,\"ime\":\"Mali mrav\",\"autor\":\"Slobodan Misic\",\"godina\":\"2020\",\"koka\":\"kola\"}";
        Knjiga knjiga = objectMapper.readValue(JSON,Knjiga.class);
        assertThat(knjiga).isEqualTo(knjiga1);

    }

}
