# Тестовое задание Spring Boot + Kotlin

## Описание

Этот проект представляет собой пример Spring Boot приложения на Kotlin, которое включает в себя следующие функции:
- Scheduled Task для парсинга и сохранения списка товаров в базу данных MongoDB.
- REST API для получения данных о товаре и его доступности

## Инструкция по запуску проекта

1. Убедитесь, что у вас установлены Docker и Docker Compose
   Перед началом убедитесь, что Docker и Docker Compose установлены на вашем компьютере. Вы можете проверить это, выполнив следующие команды:

1. Убедитесь, что у вас установлены Docker и Docker Compose:
    ```bash
    docker --version
    docker-compose --version
    ```
   Если не установлены, следуйте инструкциям на [официальном сайте Docker](https://docs.docker.com/get-docker/) для установки.

2. Настройте переменные окружения. Создайте файл `.env` в корневой папке вашего проекта со следующим содержимым:
    ```bash
    APP_PORT=8080
    MONGO_PORT=27017
    
    FETCH_ITEMS_URL=https://aitec.one/testapp/items.json
    ITEM_AVAILABILITY_URL=https://webhook.site/924bba4c-b9fd-4fc5-9307-d8f1f6ff5b0e
    FETCH_ITEMS_SCHEDULED_JOB_CRON=0 * * * * *
    ```

3. Сборка и запуск контейнеров:
    ```bash
    docker-compose up --build
    ```

4. Проверьте работу приложения. Приложение будет доступно по адресу `http://localhost:8080`.

5. Для остановки и удаления контейнеров:
    ```bash
    docker-compose down
    ```

## Запуск через командную строку

1. Убедитесь, что у вас установлен Java 17 и Gradle. Проверьте это, выполнив:
    ```bash
    java -version
    gradle -version
    ```

2. Настройте переменные окружения для MongoDB в файле `application.properties`:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/testdb
    fetch.items.url=https://aitec.one/testapp/items.json
    item.availability.url=https://webhook.site/924bba4c-b9fd-4fc5-9307-d8f1f6ff5b0e
    fetch.items.scheduled.job.cron=0 * * * * *
    ```

3. Запустите MongoDB:
    ```bash
    mongod --port 27017 --dbpath /path/to/your/mongo/data
    ```

4. Соберите и запустите проект:
    ```bash
    ./gradlew bootRun
    ```

5. Приложение будет доступно по адресу `http://localhost:8080`.
