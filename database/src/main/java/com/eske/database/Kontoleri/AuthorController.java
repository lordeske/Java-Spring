package com.eske.database.Kontoleri;


import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.domain.dto.AuthorDto;
import com.eske.database.mappers.Mapper;
import com.eske.database.services.AuthorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
       AuthorEntity savedAuthor = authorServices.createAuthor(authorEntity);
       return  new ResponseEntity<>(mapper.mapTo(savedAuthor), HttpStatus.CREATED);


    }





}
