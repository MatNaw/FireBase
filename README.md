# The Firebase Application
Aplikacja webowa do wspomagania centrali strażackiej wyznaczającej drużyny strażackie jadące na wezwanie

## 1. Frameworki / narzędzia
### Backend
#### Development
* [Spring Boot v2.2.0](http://projects.spring.io/spring-boot/)
* [Liquibase](https://www.liquibase.org/)

#### Testy
* [Swagger](https://swagger.io/)
* [Spock](http://spockframework.org/)

### Frontend
#### Development

## 2. Uruchamianie projektu
##### Backend - przy pierwszym uruchomieniu:  
W głównym katalogu projektu wykonujemy polecenie docker-compose -f ./docker/docker-compose.postgres.yml up -d

##### Frontend

### Profile Spring
| Nazwa profilu               | Opis                                                                      |
| --------------------------- | ------------------------------------------------------------------------- |
| `swagger`                   | Udostępnia UI Swaggera (http://localhost:${server.port}/swagger-ui.html)  |
| `dev`                       | Uruchamia changeset'a z danymi inicjalnymi                                |

##### Port aplikacji: `8080`
