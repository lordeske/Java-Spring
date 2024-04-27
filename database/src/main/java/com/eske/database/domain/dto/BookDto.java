package com.eske.database.domain.dto;

import com.eske.database.domain.Entities.AuthorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookDto {


    private String isbn;

    private String title;


    private AuthorDto author;

}
