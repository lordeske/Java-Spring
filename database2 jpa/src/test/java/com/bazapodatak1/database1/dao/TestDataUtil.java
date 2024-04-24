package com.bazapodatak1.database1.dao;

import doomen.Autori;
import doomen.Knjige;

public final class TestDataUtil {

    private TestDataUtil()
    {

    }
    public static Autori createTestAutor() {
        return Autori.builder()
                .id(1l)
                .ime("Mihajlo")
                .godine(17).build();
    }
  static Autori createTestAutorA() {
        return Autori.builder()
                .id(1l)
                .ime("Sinisa")
                .godine(20).build();
    }

    static Autori createTestAutorB() {
        return Autori.builder()
                .id(1l)
                .ime("Miljan")
                .godine(25).build();
    }

    static Autori createTestAutorC() {
        return Autori.builder()
                .id(1l)
                .ime("Djole")
                .godine(21).build();
    }


    public static Knjige createTestKnjiga(final Autori autori) {
        return Knjige.builder().
                isbn("1-123-456-7")
                .naslov("Mracna Carobnica")
                .autor(autori)
                .build();
    }
    static Knjige createTestKnjigaA(final Autori autori) {
        return Knjige.builder().
                isbn("1-123-456-8")
                .naslov("Avlijski glupan")
                .autor(autori)
                .build();
    }
    static Knjige createTestKnjigaB(final Autori autori) {
        return Knjige.builder().
                isbn("1-123-456-9")
                .naslov("Borac sa macem")
                .autor(autori)
                .build();
    }



}
