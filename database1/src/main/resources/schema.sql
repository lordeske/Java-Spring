create table "autori" (
    "id" bigint DEFAULT nextval ("autori_id_seg") NOT NULL,
    "ume" text ,
    "godine" integer,
    CONSTRAINT "autori_pkey" PRIMARY KEY ("id")

);

create table "knjige" (
    "isbn" text NOT NUll,
    "naslov" text ,
    "autor_id" bigint,
    CONSTRAINT "knjige_pkey" PRIMARY KEY ("isbn"),
    CONSTRAINT "fk_autor" FOREIGN KEY (autor_id)
    REFERENCES autori(id)

);

