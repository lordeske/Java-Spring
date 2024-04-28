package com.eske.database.services.impl;


import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.repositories.AuthorRepository;
import com.eske.database.services.AuthorServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<AuthorEntity> findAll() {
       return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
               .collect(Collectors.toList());
    }
}
