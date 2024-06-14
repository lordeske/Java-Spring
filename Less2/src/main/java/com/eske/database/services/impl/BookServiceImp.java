package com.eske.database.services.impl;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.repositories.BookRepository;
import com.eske.database.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return  bookRepository.findById(isbn);
    }

    @Override
    public boolean isExist(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity parcialUpdate(String isbn, BookEntity bookEntity) {

        bookEntity.setIsbn(isbn);

       return bookRepository.findById(isbn).map(
                existingBook -> {

                    Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setIsbn);

                    return bookRepository.save(existingBook);

                }
        ).orElseThrow(()-> new RuntimeException("\"Knjig ne postoji \"") );


    }

    @Override
    public void delete(String isbn) {

        bookRepository.deleteById(isbn);

    }

    @Override
    public Page<BookEntity> findALL(Pageable pageable) {
       return  bookRepository.findAll(pageable);
    }
}
