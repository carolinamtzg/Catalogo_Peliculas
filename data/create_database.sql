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
	PRIMARY KEY("id")
);

CREATE TABLE "repartos" (
	"id_pelicula"	INTEGER,
	"id_artista"	INTEGER,
	FOREIGN KEY("id_pelicula") REFERENCES "peliculas"("id"),
	PRIMARY KEY("id_pelicula","id_artista")
);

COMMIT;