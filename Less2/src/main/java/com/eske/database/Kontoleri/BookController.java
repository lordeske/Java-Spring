package com.eske.database.Kontoleri;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;
import com.eske.database.mappers.Mapper;
import com.eske.database.services.impl.BookServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {


    private Mapper<BookEntity, BookDto> bookMapper;
    private BookServiceImp bookServiceImp;


    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookServiceImp bookServiceImp) {
        this.bookMapper = bookMapper;
        this.bookServiceImp = bookServiceImp;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn,
                                                    @RequestBody BookDto bookDto) {

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExist = bookServiceImp.isExist(isbn);
        BookEntity savedBook = bookServiceImp.createBook(isbn, bookEntity);
        BookDto bookDto1 = bookMapper.mapTo(savedBook);


        if (bookExist) {

            return new ResponseEntity<>(bookDto1, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(bookDto1, HttpStatus.CREATED);

        }


    }


    @GetMapping(path = "books")
    public Page<BookDto> allBooks(Pageable pageable) {
        Page<BookEntity> books = bookServiceImp.findALL(pageable);

        return   books.map(bookMapper::mapTo);

    }


    @GetMapping(path = "books/{isbn}")
    public ResponseEntity<BookDto> findOneBook(@PathVariable("isbn") String isbn) {

        Optional<BookEntity> book = bookServiceImp.findOne(isbn);

        return book.map(BookEntity -> {
            BookDto bookDto = bookMapper.mapTo(BookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );

    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> parcialUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto
    ) {
        if (!bookServiceImp.isExist(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);

        BookEntity updatedBook = bookServiceImp.parcialUpdate(isbn, bookEntity);

        return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);

    }


    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity deleteBook(
            @PathVariable("isbn") String isbn
    )
    {

        bookServiceImp.delete(isbn);

        return new ResponseEntity(HttpStatus.NO_CONTENT);




    }




}
