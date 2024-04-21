package com.bazapodatak1.database1.dao;

import doomen.Autori;
import doomen.Knjige;

public final class TestDataUtil {

    private TestDataUtil()
    {

    }


    static Autori createTestAutor() {
        return Autori.builder()
                .id(1l)
                .ime("Mihajlo")
                .godine(20).build();
    }

    static Knjige createTestKnjiga() {
        return Knjige.builder().
                isbn("1-123-456-7")
                .naslov("Mracna Carobnica")
                .autor_id(1L)
                .build();
    }
}
