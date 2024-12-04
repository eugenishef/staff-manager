# REST микросервис & сервис выгрузки данных в CSV

## Сборка
1. Соберите проект с помощью Maven:
```bash
mvn clean package
```
2. Соберите Docker-образы:
```bash
docker-compose up --build
```
3. Проверьте доступность сервисов:
- API Gateway доступен по адресу: http://localhost:9090
- - Его Swagger http://localhost:9090/swagger-ui/index.html#
- File Export доступен по адресу: http://localhost:8082
- - Его Swagger http://localhost:8082/swagger-ui/index.html#/

## Тестирование
1. Из папки postman забрать коллекцию
2. Загрузить в локальный postman
- Если по какой-то причине postman не смог определить глобальные перменные, тогда указать для:
- - baseUrl значение http://localhost:9090
- - exporterUrl значение http://localhost:8082
3. Выбрать Run collection -> Run staff-manager / либо подергать методы самостоятельно
4. В папке пользователя создается папка myExports в которую выгружается CSV в формате, employees_YYYYMMDD_HHMMSS.csv

## Используемые ресурсы
- [Pagination and Sorting using Spring Data JPA](https://www.baeldung.com/spring-data-jpa-pagination-sorting)
- [Understanding DTO in Java Spring Boot: A Step-by-Step Tutorial](https://medium.com/@alitrgttt/understanding-dto-in-java-spring-boot-a-step-by-step-tutorial-016b17ed0061)
- [Что такое Mapstruct и как правильно настроить его для модульного тестирования в SpringBoot приложениях](https://javarush.com/groups/posts/3698-chto-takoe-mapstruct-i-kak-praviljhno-nastroitjh-ego-dlja-moduljhnogo-testirovanija-v-springboo)
- [Introduction to Apache Commons CSV](https://www.baeldung.com/apache-commons-csv)
- [Асинхронное выполнение в Spring. Аннотации @EnableAsync и @Async](https://www.tune-it.ru/web/romo/blog/-/blogs/12523232)
- [How to work with Files in Java](https://beknazarsuranchiyev.medium.com/how-to-work-with-files-in-java-5f5d76012d63)
- [Read / Write CSV files in Java using Apache Commons CSV](https://www.callicoder.com/java-read-write-csv-file-apache-commons-csv/)
- [Документирование SpringBoot API с помощью Swagger](https://struchkov.dev/blog/ru/api-swagger/#)
- [Regexr](https://regexr.com/)