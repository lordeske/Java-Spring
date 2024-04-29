package com.eske.database.services;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;

import java.util.List;

public interface BookService {

   BookEntity createBook (String isbn,BookEntity bookEntity);


    List<BookEntity> findAll();

    ;
}
