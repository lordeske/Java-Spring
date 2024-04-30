package com.eske.database.kontoleri;


import com.eske.database.TestDataUtil;
import com.eske.database.domain.Entities.AuthorEntity;
import com.eske.database.domain.Entities.BookEntity;
import com.eske.database.domain.dto.BookDto;
import com.eske.database.repositories.BookRepository;
import com.eske.database.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class BookControllerIntegartionTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerIntegartionTest( BookService bookServic,MockMvc mockMvc,ObjectMapper objectMapper)
    {
        this.mockMvc= mockMvc;
        this.objectMapper =  new ObjectMapper();
        this.bookService = bookServic;
    }


    @Test
    public void TestBookIsCreated() throws Exception {

        BookDto bookDto = TestDataUtil.createTestBookEntityADTO(null);
        String bookJSON = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON))


                .andExpect(MockMvcResultMatchers.status().isCreated());



    }

    @Test
    public void TestBookIsCreatedValues() throws Exception {

        BookDto bookDto = TestDataUtil.createTestBookEntityADTO(null);
        String bookJSON = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJSON))


                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle()))         ;



    }

    @Test
    public void TestReadALlBooks() throws Exception {

      mockMvc.perform(MockMvcRequestBuilders.get("/books")
              .contentType(MediaType.APPLICATION_JSON) )

              .andExpect(
                      MockMvcResultMatchers.status().isOk()
              );
    }

    @Test
    public void TestReadALlBooksRespBody() throws Exception {

       BookEntity bookEntity = TestDataUtil.createTestBookEntityA(null);
       bookService.createBook(bookEntity.getIsbn(),bookEntity);


        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON) )


                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value("978-1-2345-6789-0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Shadow in the Attic"))        ;


                ;
    }



    @Test
    public void  TestBookExistStatus() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookEntityA(null);
        bookService.createBook(testBook.getIsbn(),testBook);


        mockMvc.perform(MockMvcRequestBuilders.get("/books/"+testBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON))


                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );

    }

    @Test
    public void  TestBookExistJSONNotEXist() throws Exception {
        BookEntity testBook = TestDataUtil.createTestBookEntityA(null);


        mockMvc.perform(MockMvcRequestBuilders.get("/books/"+testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON))


                .andExpect(
                        MockMvcResultMatchers.status().isNotFound()
                );

    }


    @Test
    public void create2020bookUpdate() throws Exception {
        BookEntity bookEntity=  TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createBook(

                bookEntity.getIsbn(), bookEntity
        );

        BookDto testBookA = TestDataUtil.createTestBookEntityADTO(null);
        testBookA.setIsbn(savedBook.getIsbn());

        String json = objectMapper.writeValueAsString(testBookA);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/978-1-2345-6789-0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))


                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );








    }


    @Test
    public void create201bookUpdate() throws Exception {
        BookEntity bookEntity=  TestDataUtil.createTestBookEntityA(null);
        BookEntity savedBook = bookService.createBook(

                bookEntity.getIsbn(), bookEntity
        );

        BookDto testBookA = TestDataUtil.createTestBookEntityADTO(null);
        testBookA.setIsbn(savedBook.getIsbn());
        testBookA.setTitle("Novo ime");

        String json = objectMapper.writeValueAsString(testBookA);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))


                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")

                ) .andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value("Novo ime")

                );








    }




}
