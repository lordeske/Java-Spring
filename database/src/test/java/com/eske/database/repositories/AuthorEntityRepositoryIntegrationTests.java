package com.eske.database.repositories;

import com.eske.database.TestDataUtil;
import com.eske.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorEntityA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        assertThat(result)
                .hasSize(3).
                containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorA);
        authorA.setName("UPDATED");
        underTest.save(authorA);
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorA);
        underTest.deleteById(authorA.getId());
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        Author testAuthorAEntity = TestDataUtil.createTestAuthorEntityA();
        underTest.save(testAuthorAEntity);
        Author testAuthorBEntity = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorBEntity);
        Author testAuthorCEntity = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorCEntity);

        Iterable<Author> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorBEntity, testAuthorCEntity);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        Author testAuthorAEntity = TestDataUtil.createTestAuthorEntityA();
        underTest.save(testAuthorAEntity);
        Author testAuthorBEntity = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorBEntity);
        Author testAuthorCEntity = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorCEntity);

        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).containsExactly(testAuthorAEntity);
    }


}
