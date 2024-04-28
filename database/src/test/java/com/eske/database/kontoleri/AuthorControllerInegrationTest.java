package com.eske.database.kontoleri;

import com.eske.database.TestDataUtil;
import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.services.AuthorServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AuthorControllerInegrationTest {


    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthorServices authorServices;

    @Autowired
    public AuthorControllerInegrationTest(AuthorServices authorServices, MockMvc mockMvc,ObjectMapper objectMapper)
    {
        this.mockMvc= mockMvc;
        this.objectMapper = objectMapper;
        this.authorServices = authorServices;
    }




    @Test
    public void TestCreateAuthor() throws Exception {
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        testAuthorB.setId(null);


        String authorJson = objectMapper.writeValueAsString(testAuthorB);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson))


                .andExpect(MockMvcResultMatchers.status().isCreated());


    }


    @Test
    public void TestCreateAuthorJSON() throws Exception {
        AuthorEntity testAuthorB = TestDataUtil.createTestAuthorB();
        testAuthorB.setId(null);


        String authorJson = objectMapper.writeValueAsString(testAuthorB);

        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))


                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Thomas Cronin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(44));


    }

    @Test
    public void TestReadALlAuthors() throws Exception {

        authorServices.createAuthor(TestDataUtil.createTestAuthorB());

        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Thomas Cronin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(44));




    }













}
