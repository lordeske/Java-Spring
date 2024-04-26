package com.eske.database.Kontoleri;


import com.eske.database.domain.dto.AuthorDto;
import com.eske.database.services.AuthorServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    AuthorServices authorServices;

    public  AuthorController(AuthorServices authorServices)
    {
        this.authorServices = authorServices;
    }



    @PostMapping(path = "/authors")
    public  AuthorDto createAuthor(@RequestBody AuthorDto author)
    {
        return  authorServices.createAuthor(author);


    }





}
