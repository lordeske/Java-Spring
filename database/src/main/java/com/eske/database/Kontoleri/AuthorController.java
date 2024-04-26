package com.eske.database.Kontoleri;


import com.eske.database.domain.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @PostMapping(path = "/authors")
    public  Author createAuthor()
    {


        return null;
    }





}
