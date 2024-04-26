package com.eske.database.mappers.impl;

import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.domain.dto.AuthorDto;
import com.eske.database.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {


    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }


    @Override
    public AuthorEntity mapTo(AuthorDto authorDto) {
        return  modelMapper.map(authorDto, AuthorEntity.class);

    }

    @Override
    public AuthorDto mapFrom(AuthorEntity authorEntity) {
        return  modelMapper.map(authorEntity,AuthorDto.class);
    }
}
