package com.eske.database.services;

import com.eske.database.domain.Entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorServices {


    AuthorEntity save(AuthorEntity authorEntity);


    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExist(Long id);

    AuthorEntity parcialUpdate(Long id, AuthorEntity authorEntity);
}
