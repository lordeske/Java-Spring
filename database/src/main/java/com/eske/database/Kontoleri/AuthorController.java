package com.eske.database.Kontoleri;


import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.domain.dto.AuthorDto;
import com.eske.database.mappers.Mapper;
import com.eske.database.services.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorServices authorServices;
    private Mapper<AuthorEntity, AuthorDto> mapper;

    public  AuthorController(AuthorServices authorServices,Mapper<AuthorEntity, AuthorDto> mapper)
    {
        this.authorServices = authorServices;
        this.mapper = mapper;
    }





    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author)
    {
       AuthorEntity authorEntity = mapper.mapFrom(author);
       AuthorEntity savedAuthor = authorServices.save(authorEntity);
       return  new ResponseEntity<>(mapper.mapTo(savedAuthor), HttpStatus.CREATED);


    }


    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors()
    {
        List<AuthorEntity> authors = authorServices.findAll();
        return authors.stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }


    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable ("id") Long id)
    {

       Optional<AuthorEntity> foundAuthor = authorServices.findOne(id);
        return foundAuthor.map(authorEntity -> {
            AuthorDto autor = mapper.mapTo(authorEntity);
            return new ResponseEntity<>(autor, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }


    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable ("id") Long id,
                                                      @RequestBody AuthorDto authorDto)
    {
      if (!authorServices.isExist(id))
      {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      authorDto.setId(id);
      AuthorEntity authorEntity=  mapper.mapFrom(authorDto);

     AuthorEntity savedAuthot = authorServices.save(authorEntity);
     return new ResponseEntity<>(mapper.mapTo(savedAuthot),HttpStatus.OK);


    }



    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> parcialUpdateAuthor(
            @PathVariable ("id") Long id,
            @RequestBody AuthorDto authorDto
    )
    {

        if (!authorServices.isExist(id))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = mapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor =   authorServices.parcialUpdate(id, authorEntity);
        return  new ResponseEntity<>(mapper.mapTo(updatedAuthor),HttpStatus.OK )  ;


    }


    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(
            @PathVariable ("id") Long id
    )
    {
       authorServices.delete(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;



    }






    }
