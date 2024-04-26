package com.eske.database.services.impl;


import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.repositories.AuthorRepository;
import com.eske.database.services.AuthorServices;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorServices {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository)
    {
        this.authorRepository= authorRepository;
    }


    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {

        return authorRepository.save(authorEntity);


    }
}
