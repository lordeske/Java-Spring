package com.eske.database.Kontoleri;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;
import com.eske.database.mappers.Mapper;
import com.eske.database.services.impl.BookServiceImp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {


    private Mapper<BookEntity,BookDto> bookMapper;
    private BookServiceImp bookServiceImp;


    public BookController(Mapper<BookEntity,BookDto> bookMapper,BookServiceImp bookServiceImp)
    {
        this.bookMapper= bookMapper;
        this.bookServiceImp = bookServiceImp;
    }

    @PostMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn,
                @RequestBody BookDto bookDto)
    {
         BookEntity bookEntity  = bookMapper.mapFrom(bookDto);
         BookEntity savedBook  =bookServiceImp.createBook(isbn,bookEntity);
         return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.CREATED);

    }





}
