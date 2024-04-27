package com.eske.database.kontoleri;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BookControllerIntegartionTest {

    private MockMvc mockMvc;

    @Autowired
    public BookControllerIntegartionTest(MockMvc mockMvc)
    {
        this.mockMvc= mockMvc;
    }


    @Test
    public void TestBookIsCreated()
    {
        
    }



}
