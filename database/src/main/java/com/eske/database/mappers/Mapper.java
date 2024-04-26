package com.eske.database.mappers;

import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.domain.dto.AuthorDto;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);



}
