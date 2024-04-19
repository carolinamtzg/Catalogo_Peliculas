CREATE TABLE "artistas" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"url_foto"	TEXT,
	"url_web"	TEXT,
	PRIMARY KEY("id")
	);

CREATE TABLE "directores" (
	"id"	INTEGER,
	"nombre"	TEXT,
	"url_foto"	TEXT,
	"url_web"	TEXT,
	PRIMARY KEY("id")
	);

CREATE TABLE "generos" (
	"id"	INTEGER,
	"descripcion"	TEXT,
	PRIMARY KEY("id")
);

CREATE TABLE "peliculas" (
	"id"	INTEGER,
	"titulo"	TEXT,
	"id_director"	INTEGER,
	"anyo"	INTEGER,
	"url_caratula"	TEXT,
	"id_genero"	INTEGER,
	"es_animacion"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id_director") REFERENCES "directores"("id"),
	FOREIGN KEY("id_genero") REFERENCES "generos"("id")
	);

CREATE TABLE "repartos" (
	"id_pelicula"	INTEGER,
	"id_artista"	INTEGER,
	FOREIGN KEY("id_pelicula") REFERENCES "peliculas"("id"),
	FOREIGN KEY("id_artista") REFERENCES "artistas"("id"),
	PRIMARY KEY("id_pelicula","id_artista")
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

INSERT INTO directores(id,nombre,url_foto,url_web) VALUES(1,"John Lasseter", "www.john_lasseter.png","www.john_lasseter.com");
INSERT INTO directores(id,nombre,url_foto,url_web) VALUES(2,"Quentin Tarantino", "www.quentin_tarantino.png","www.quentin_tarantino.com");
INSERT INTO directores(id,nombre,url_foto,url_web) VALUES(3,"Steven Spielberg", "www.steven_spielberg.png","www.steven_spielberg.com");
INSERT INTO directores(id,nombre,url_foto,url_web) VALUES(4,"Stanley Kubrick", "www.stanley_kubrick.png","www.stanley_kubrick.com");
INSERT INTO directores(id,nombre,url_foto,url_web) VALUES(5,"David Fincher", "www.david_fincher.png","www.david_fincher.com");
INSERT INTO directores(id,nombre,url_foto,url_web) VALUES(6,"Martin Scorsese", "www.martin_scorsese.png","www.martin_scorsese.com");

INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(1,"Tom Hanks", "www.tom_hanks.png","www.tom_hanks.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(2,"Owen Wilson", "www.owen_wilson.png","www.owen_wilson.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(3,"John Travolta", "www.john_travolta.png","www.john_travolta.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(4,"Uma Thurman", "www.uma_thurman.png","www.uma_thurman.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(5,"Lucy Liu", "www.lucy_liu.png","www.lucy_liu.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(6,"Robert De Niro", "www.robert_de_niro.png","www.robert_de_niro.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(7,"Leonardo DiCaprio", "www.leonardo_dicaprio.png","www.leonardo_dicaprio.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(8,"Matt Damon", "www.matt_damon.png","www.matt_damon.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(9,"Sam Neill", "www.sam_neill.png","www.sam_neill.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(10,"Brad Pitt", "www.brad_pitt.png","www.brad_pitt.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(11,"Edward Norton", "www.edward_norton.png","www.edward_norton.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(12,"Jesse Eisenberg", "www.jesse_eisenberg.png","www.jesse_eisenberg.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(13,"Andrew Garfield", "www.andrew_garfield.png","www.andrew_garfield.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(14,"Laura Dern", "www.laura_dern.png","www.laura_dern.com");
INSERT INTO artistas(id,nombre,url_foto,url_web) VALUES(15,"Larry the Cable Guy", "www.larry_the_cable_guy.png","www.larry_the_cable_guy.com");

INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (1, "Toy Story", 1, 1995, "caratula_toy_story.png", 3,1);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (2, "Cars", 1, 2006, "caratula_cars.png", 3,1);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (3, "Pulp Fiction", 2, 1994, "caratula_pulp_fiction.png", 8,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (4, "Kill Bill: Vol. 1", 2, 2003, "caratula_kill_bill_vol_1.png", 1,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (5, "Goodfellas", 6, 1990, "caratula_goodfellas.png", 8,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (6, "Jurassic Park", 3, 1993, "caratula_jurassic_park.png", 6,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (7, "Saving Private Ryan", 3, 1998, "caratula_saving_private_ryan.png", 1,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (8, "Fight Club", 5, 1999, "caratula_fight_club.png", 8,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (9, "The Social Network", 5, 2010, "caratula_the_social_network.png", 10,0);
INSERT INTO peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES (10, "The Departed", 6, 2006, "caratula_the_departed.png", 8,0);

INSERT INTO repartos (id_pelicula, id_artista) VALUES (1,1);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (2,2);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (2,15);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (3,3);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (3,4);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (4,4);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (4,5);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (5,6);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (6,9);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (6,14);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (7,1);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (7,8);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (8,10);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (8,11);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (9,12);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (9,13);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (10,7);
INSERT INTO repartos (id_pelicula, id_artista) VALUES (10,8);

COMMIT;