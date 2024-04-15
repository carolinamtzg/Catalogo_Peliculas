CREATE TABLE "artistas" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"url_foto"	TEXT,
	"url_web"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "repartos"("id_artista")
);

CREATE TABLE "directores" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"url_foto"	TEXT,
	"url_web"	TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "peliculas"("id_director")
);

CREATE TABLE "generos" (
	"id"	INTEGER,
	"descripcion"	TEXT,
	FOREIGN KEY("id") REFERENCES "peliculas"("id_genero"),
	PRIMARY KEY("id")
);

CREATE TABLE "peliculas" (
	"id"	INTEGER,
	"titulo"	TEXT,
	"id_director"	INTEGER,
	"año"	INTEGER,
	"url_caratula"	TEXT,
	"id_genero"	INTEGER,
	"es_animacion"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id_director") REFERENCES "directores"("id"),
	FOREIGN KEY("id_genero") REFERENCES "generos"("id"),
	FOREIGN KEY("id") REFERENCES "repartos"("id_pelicula")
);

CREATE TABLE "repartos" (
	"id_pelicula"	INTEGER,
	"id_artista"	INTEGER,
	PRIMARY KEY("id_pelicula","id_artista"),
	FOREIGN KEY("id_pelicula") REFERENCES "peliculas"("id"),
	FOREIGN KEY("id_artista") REFERENCES "artistas"("id")
);

INSERT INTO generos (id, descripcion) VALUES (1, "ACCION");
INSERT INTO generos (id, descripcion) VALUES (2, "AVENTURA");
INSERT INTO generos (id, descripcion) VALUES (3, "COMEDIA");
INSERT INTO generos (id, descripcion) VALUES (4, "DRAMA");
INSERT INTO generos (id, descripcion) VALUES (5, "TERROR");
INSERT INTO generos (id, descripcion) VALUES (6, "CIENCIA_FICCION");
INSERT INTO generos (id, descripcion) VALUES (7, "MUSICAL_DANZA");
INSERT INTO generos (id, descripcion) VALUES (8, "SUSPENSO");
INSERT INTO generos (id, descripcion) VALUES (9, "WESTERN");
INSERT INTO generos (id, descripcion) VALUES (10, "DOCUMENTAL");
INSERT INTO generos (id, descripcion) VALUES (11, "BIOGRAFICO");
INSERT INTO generos (id, descripcion) VALUES (12, "ROMANCE");

COMMIT;