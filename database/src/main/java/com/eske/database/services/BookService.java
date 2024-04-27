package com.eske.database.services;

import com.eske.database.domain.Entities.BookEntity;

public interface BookService {

   BookEntity createBook (String isbn,BookEntity bookEntity);



}
