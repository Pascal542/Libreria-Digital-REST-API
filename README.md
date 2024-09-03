#  Sistema de Gestión de Biblioteca
REST API para una libreria digital donde se gestionan libros con sus datos respectivos y estados

## Descripción

Esta es una aplicación Spring Boot para gestionar una biblioteca digital. Proporciona varios endpoints para crear, leer, actualizar y eliminar libros en la biblioteca. La aplicación también incluye consultas personalizadas para filtrar libros por autor, rango de fechas y contar libros según su estado.

## Funcionalidades

- **Crear un Libro**: Agregar nuevos libros a la biblioteca.
- **Obtener Libros**: Recuperar una lista de libros con filtrado opcional por autor o estado.
- **Obtener Libro por ID**: Recuperar un libro específico usando su ID.
- **Actualizar un Libro**: Actualizar los detalles de un libro existente.
- **Eliminar un Libro**: Eliminar un libro de la biblioteca usando su ID.
- **Consultas Personalizadas**:
  - **Libros por Autor**: Recuperar libros escritos por un autor específico.
  - **Libros por Rango de Fechas**: Recuperar libros publicados dentro de un rango de fechas específico.
  - **Contar Libros Disponibles**: Contar el número de libros actualmente disponibles.
  - **Contar Libros Prestados**: Contar el número de libros actualmente prestados.

### Prerrequisitos

- Java 17
- MySQL

### Ejecución de la Aplicación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/Pascal542/Libreria-Digital-REST-API.git
   cd Libreria-Digital-REST-API
    
2. Configurar la coneccion a la base de datos
Se tiene que configurar manualmente la coneccion escribiendo las credenciales de tu usuario MySQL
y creando tambien una base de datos llamada books

 
Puedes crear la base de datos con el siguiente comando

  CREATE DATABASE books;
  USE books; 

Configurar el application.properties del proyecto

  SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/books 
  SPRING_DATASOURCE_USERNAME=username
  SPRING_DATASOURCE_PASSWORD=password

Crear la app con

./mvnw clean install

Ejecutarla con 

./mvnw spring-boot:run


## Base URL

`http://localhost:8080/v1/books`

## Endpoints

### 1. Crear un Libro

- **POST** `/v1/books`
- **Descripción**: Crea un nuevo libro en la colección.
- **Request Body**:
  ```json
  {
    "title": "Título del libro",
    "author": "Autor del libro",
    "isbn": "Código ISBN del libro",
    "publishedDate": "Fecha de publicación (yyyy-MM-dd)",
    "status": "Estado del libro (AVAILABLE o BORROWED)"
  }

## Response:
    Código 201 Created: Libro creado exitosamente.
    Error: 400 Bad Request (si hay problemas con la validación).

### 2. Obtener Libros

    GET /v1/books
    Descripción: Obtiene una lista de libros. Se puede filtrar por autor o estado.
    Query Parameters:
        author (opcional): Nombre del autor para filtrar los libros.
        status (opcional): Estado del libro para filtrar (AVAILABLE o BORROWED).
    Response:
        Código 200 OK: Lista de libros que cumplen con los criterios especificados.

### 3. Obtener Libro por ID

    GET /v1/books/{id}
    Descripción: Obtiene los detalles de un libro específico usando su ID.
    Path Variable:
        id: ID del libro.
    Response:
        Código 200 OK: Detalles del libro.
        Error: 404 Not Found (si el libro con el ID especificado no existe).

### 4. Actualizar un Libro

    PUT /v1/books/{id}
    Descripción: Actualiza los detalles de un libro existente usando su ID.
    Path Variable:
        id: ID del libro a actualizar.
    Request Body:

    json

    {
      "title": "Título actualizado",
      "author": "Autor actualizado",
      "isbn": "Código ISBN actualizado",
      "publishedDate": "Fecha de publicación actualizada (yyyy-MM-dd)",
      "status": "Estado actualizado (AVAILABLE o BORROWED)"
    }

    Response:
        Código 200 OK: Libro actualizado exitosamente.
        Error: 400 Bad Request (si hay problemas con la validación), 404 Not Found (si el libro con el ID especificado no existe).

### 5. Eliminar un Libro

    DELETE /v1/books/{id}
    Descripción: Elimina un libro de la colección usando su ID.
    Path Variable:
        id: ID del libro a eliminar.
    Response:
        Código 200 OK: Mensaje indicando que el libro ha sido eliminado.
        Error: 404 Not Found (si el libro con el ID especificado no existe).

### 6. Obtener Libros por Autor

    GET /v1/books/author
    Descripción: Obtiene una lista de libros filtrados por autor.
    Query Parameter:
        author: Nombre del autor para filtrar los libros.
    Response:
        Código 200 OK: Lista de libros del autor especificado.

### 7. Obtener Libros por Rango de Fechas

    GET /v1/books/date-range
    Descripción: Obtiene una lista de libros dentro de un rango de fechas de publicación.
    Query Parameters:
        startDate: Fecha de inicio del rango (formato yyyy-MM-dd).
        endDate: Fecha de fin del rango (formato yyyy-MM-dd).
    Response:
        Código 200 OK: Lista de libros dentro del rango de fechas especificado.

### 8. Contar Libros Disponibles

    GET /v1/books/count/available
    Descripción: Obtiene el número total de libros disponibles.
    Response:
        Código 200 OK: Número total de libros disponibles.

### 9. Contar Libros Prestados

    GET /v1/books/count/borrowed
    Descripción: Obtiene el número total de libros prestados.
    Response:
        Código 200 OK: Número total de libros prestados.
