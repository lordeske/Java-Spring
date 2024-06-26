package com.eske.database.services.impl;


import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.repositories.AuthorRepository;
import com.eske.database.services.AuthorServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public AuthorEntity save(AuthorEntity authorEntity) {

        return authorRepository.save(authorEntity);


    }

    @Override
    public List<AuthorEntity> findAll() {
       return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
               .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
       return  authorRepository.findById(id);
    }

    @Override
    public boolean isExist(Long id) {
         return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity parcialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

       return
               authorRepository.findById(id)
                       .map(
                               existingAuthor -> {

                                   Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
                                   Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);

                                   return authorRepository.save(existingAuthor);
                               }
                       ).orElseThrow(()-> new RuntimeException("Autor ne postoji"));




    }

    @Override
    public void delete(Long id) {

        authorRepository.deleteById(id);

    }

    @Override
    public Page<AuthorEntity> findALL(Pageable pageable) {
     return   authorRepository.findAll(pageable);
    }
}
