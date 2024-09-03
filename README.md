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

  ```bash
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
