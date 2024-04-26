package com.eske.database.domain.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AuthorDto {

    private Long id;

    private String name;

    private Integer age;


}
