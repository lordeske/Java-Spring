package com.eske.database.services;

import com.eske.database.domain.Entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorServices {


    AuthorEntity createAuthor(AuthorEntity authorEntity);


    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);
}
