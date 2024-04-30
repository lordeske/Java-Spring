package com.eske.database.services;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

   BookEntity createBook (String isbn,BookEntity bookEntity);


    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExist(String isbn);


    BookEntity parcialUpdate(String isbn, BookEntity bookEntity);
}
