package com.eske.database.Kontoleri;

import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;
import com.eske.database.mappers.Mapper;
import com.eske.database.services.impl.BookServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {


    private Mapper<BookEntity,BookDto> bookMapper;
    private BookServiceImp bookServiceImp;


    public BookController(Mapper<BookEntity,BookDto> bookMapper,BookServiceImp bookServiceImp)
    {
        this.bookMapper= bookMapper;
        this.bookServiceImp = bookServiceImp;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn,
                                                    @RequestBody BookDto bookDto)
    {

         BookEntity bookEntity  = bookMapper.mapFrom(bookDto);
         boolean bookExist = bookServiceImp.isExist(isbn);
         BookEntity savedBook  =bookServiceImp.createBook(isbn,bookEntity);
         BookDto bookDto1= bookMapper.mapTo(savedBook);


        if (bookExist)
         {

             return  new ResponseEntity<>(bookDto1, HttpStatus.OK);
         }
         else
         {

             return new ResponseEntity<>(bookDto1, HttpStatus.CREATED);

         }



    }


    @GetMapping(path = "books")
    public List<BookDto> allBooks ()
    {
        List<BookEntity> books = bookServiceImp.findAll();
        return books.stream().map(bookMapper::mapTo)
                .collect(Collectors.toList());

    }


    @GetMapping(path = "books/{isbn}")
    public ResponseEntity<BookDto> findOneBook(@PathVariable ("isbn") String isbn)
    {

        Optional<BookEntity> book =  bookServiceImp.findOne(isbn);

       return book.map(BookEntity -> {
            BookDto bookDto = bookMapper.mapTo(BookEntity);
            return new ResponseEntity<>(bookDto , HttpStatus.OK);
        }).orElse(
                new  ResponseEntity<>(HttpStatus.NOT_FOUND)
        );

    }





}
