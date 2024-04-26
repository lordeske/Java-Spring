package com.eske.database.mapperconfig;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {


    @Bean
    public ModelMapper modelMapper ()
    {

        return new ModelMapper();

    }



}
