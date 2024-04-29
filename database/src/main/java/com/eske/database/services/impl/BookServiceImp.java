package com.eske.database.services.impl;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.repositories.BookRepository;
import com.eske.database.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImp implements BookService  {


    private BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }


    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
       return  bookRepository.save(bookEntity);

    }

    public List<BookEntity> findAll() {

      return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
               .collect(Collectors.toList());

    }
}
