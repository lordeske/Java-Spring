package com.eske.database.mappers.impl;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;
import com.eske.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImplementaiton implements Mapper<BookEntity, BookDto> {



    private ModelMapper modelMapper;
    public BookMapperImplementaiton(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    @Override
    public BookDto mapTo(BookEntity bookEntity) {

        return modelMapper.map(bookEntity, BookDto.class);

    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
