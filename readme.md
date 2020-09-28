
    Warning! LOMBOK

####для запуска надо подготовить бд постгрес
-с помощью docker

    docker start --name simply-postgres -e POSTGRES_USER=simply -e POSTGRES_PASSWORD=simply -e POSTGRES_DB=simply -p 5432:5432 postgres:12
    
-с помощью параметров, перегрузив их:

    1. spring.datasource.url
    2. spring.datasource.username
    3. spring.datasource.password

Например    
    
    ./gradlew bootRun -x test --args='--spring.datasource.url=<урл до базы данных> --spring.datasource.password=<ваш пароль>'
    
-отредактировав фаил application.properties    

####сборка и запуск проекта

    ./gradlew bootRun -x test

Структура БД разворачивается с помощью liquibase.    
Не понял что имелось в виду: 'Уделить внимание логированию работы сервиса', но повесил маркирующий запросы фильтр LoggingFilter и прокинул ид запроса в логи, также добавил LoggingFeature.

протестировать работоспособность можно с помощью теста: 

    TestClient
  