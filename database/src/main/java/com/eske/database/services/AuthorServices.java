package com.eske.database.services;

import com.eske.database.domain.Entities.AuthorEntity;

import java.util.List;

public interface AuthorServices {


    AuthorEntity createAuthor(AuthorEntity authorEntity);


    List<AuthorEntity> findAll();
}
