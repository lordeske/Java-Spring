package com.eske.database.kontoleri;

import com.eske.database.TestDataUtil;
import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.domain.dto.AuthorDto;
import com.eske.database.services.AuthorServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

        authorServices.save(TestDataUtil.createTestAuthorB());

        mockMvc.perform(MockMvcRequestBuilders.get("/authors")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Thomas Cronin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(44));




    }


    @Test
    public void testFindAuthorStatus() throws Exception {

        authorServices.save(TestDataUtil.createTestAuthorB());



        mockMvc.perform(MockMvcRequestBuilders
                .get("/authors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @Test
    public void testFindAuthorID() throws Exception {


        AuthorEntity authorEntity = TestDataUtil.createTestAuthorEntityA();
        authorServices.save(authorEntity);




        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Abigail Rose"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(80));


    }



    @Test
    public void Test404UpdateAutor() throws Exception {
        AuthorDto test = TestDataUtil.createTestAutorDTDA();
        String json = objectMapper.writeValueAsString(test);

        mockMvc.perform(MockMvcRequestBuilders.put("/authors/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    public void Test200UpdateAutor() throws Exception {
        AuthorEntity test = TestDataUtil.createTestAuthorB();
        AuthorEntity authorEntity =  authorServices.save(test);
        String json = objectMapper.writeValueAsString(test);

        mockMvc.perform(MockMvcRequestBuilders.put("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void TestExisitUpdateAuthor() throws Exception {
        AuthorEntity testAuthor = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity saved = authorServices.save(testAuthor);

        AuthorEntity testAuthor2 = TestDataUtil.createTestAuthorB();

        testAuthor2.setId(saved.getId());
        String json  = objectMapper.writeValueAsString(testAuthor2);

        mockMvc.perform(MockMvcRequestBuilders.put("/authors/"+saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(saved.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testAuthor2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(testAuthor2.getAge()));



    }



    @Test
    public void paricalUdpateExist() throws Exception {

        AuthorEntity testAuthor = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity saved = authorServices.save(testAuthor);

        AuthorEntity testAuthor2 = TestDataUtil.createTestAuthorB();

        testAuthor2.setId(saved.getId());
        testAuthor2.setName("Mile Radoje");
        String json  = objectMapper.writeValueAsString(testAuthor2);

        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/"+saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(MockMvcResultMatchers.status().isOk());


    }


    @Test
    public void paricalUdpateExistValues() throws Exception {

        AuthorEntity testAuthor = TestDataUtil.createTestAuthorEntityA();
        AuthorEntity saved = authorServices.save(testAuthor);

        AuthorDto testAuthor2 = TestDataUtil.createTestAutorDTDA();


        testAuthor2.setName("Mile Radoje");
        testAuthor2.setAge(80);
        String json  = objectMapper.writeValueAsString(testAuthor2);

        mockMvc.perform(MockMvcRequestBuilders.patch("/authors/"+saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(saved.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mile Radoje"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(saved.getAge()));



    }














}
