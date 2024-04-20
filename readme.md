# Práctica de Desarrollo de soluciones con Java

## Descripción de la práctica

Crear una pequeña app visual que nos muestre todas las peliculas del catalogo
en una tabla con las siguientes columnas:

- Titulo
- Nombre del director
- año
- genero
- Animacion S/N

1. Crear el fichero create_database.sql
2. Crear las clases de cada entidad que consideres necesaria y, quizás la posibilidad de
   usar Enums.
3. Crear el DAO de directores, peliculas y artistas con los siguientes métodos:

   a. _dameTodosDirectores:_ Devolverá un ArrayList<> ordenados por nombre. _dameTodosPeliculas:_ Debe devolver las películas ordenadas por genero, año descencing, titulo.
   b. _buscaPor(id):_ Devolverá una instancia de Director o null si no lo encuentra.
   c. _buscaPor(nombre):_ Devolverá una instancia de Director o null si no lo encuentra.
   d. _borra(id):_ Borrará el director si existe.
   e. _modifica(Director director)_

4. Crear una ventana con javax.swing que muestre el listado de películas indicado: esta es una tabla con el catalogo de peliculas, este incluye (titulo, año, genero y director).
