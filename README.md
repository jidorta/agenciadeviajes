# Agencia de Viajes

Proyecto de ejemplo de **API para una agencia de viajes** desarrollado con **Spring Boot, Java y PostgreSQL**.  

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Maven
- Mockito / JUnit 5 para tests
- Docker (opcional, para levantar la base de datos)

---
## Base de datos

- PostgreSQL
- Nombre de la base: `agenciadb`
- Puede levantarse con Docker mediante un `docker-compose.yaml`.

Ejemplo de conexión en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agenciadb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

