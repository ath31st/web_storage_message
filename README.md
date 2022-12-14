## REST-сервис хранения сообщений

### Функциональность

- Реализовать регистрацию и аутентификацию пользователей сервиса
- Реализовать сохранение сообщений в базе данных, а также запрос последних 10 сообщений по каждому пользователю

### Требования

- Вы можете использовать Java или Kotlin (любой версии)
- Используйте Spring или Spring Boot
- Сообщения храните в реляционной базе - можно использовать любую (H2, MySQL, Postgres)
- Для аутентификации пользователей использовать JWT токен
- В базе данных создать несколько таблиц со связями
- Добавить описание и инструкцию по запуску и комментарии в коде, если изменяете формат сообщений, то подробное описание ендпоинтов и их полей
- Поместить все компоненты в докер, покрыть код тестами

### Спецификация

- Endpoints:
  * `POST /api/auth/register` Принимает User, используется для регистрации нового пользователя, возвращает токен. Пример: `{"name": "Vasya","password": "qwerty"}`, ответ: `{"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIERldGFpbHMiLCJuYW1lIjoiTWFrc2ltIiwiaWF0IjoxNjU5MzAwMzUxfQ.XyfV0XpeLjhhZW8cw-nd30C3p1HZUwG6sDwCWwHXG3A"}`
  * `POST /api/auth/login` Принимает уже зарегистрированного User, используется для возвращает токена. Пример: `{"name": "Vasya","password": "qwerty"}`
  * `POST /api/message/new` Принимает Message от аутентифицированного пользователя, сохраняет его в базу данных, возвращает текст сообщения. Пример: `{"name":"Vasya","message":"Hello!"}`, ответ: `{"message": "Hello!"}`
  * `POST /api/message/new` При отправке Message, с текстом "history 10" от аутентифицированного пользователя, возвращает 10 последних сохраненных сообщений данного пользователя. Пример:`{"name":"Vasya","message":"history 10"}`, ответ: `{"last messages: ":[{"message":"Hello!"},{"message":"history 12310"},{"message":"history 12310"},{"message":"history 12310"},{"message":"history 1210"},{"message":"history 1210"},{"message":"history 1210"},{"message":"history 110"},{"message":"history 110"},{"message":"history 110"}]}`
  * `GET /api/user/info` Возвращает данные о пользователе и все его сохраненные сообщения

### Результаты

- В ходе написания проекта использована база данных SqLite
- В базе данных хранится две связанных таблицы user и message, foreign key в данном случае является "user_id"
- Добавлен докерфайл, позволяющий собрать проект в докер образ и запустить его в контейнере
- Для фильтрации и генерации токенов использована библиотека [Java JWT](https://mvnrepository.com/artifact/com.auth0/java-jwt)
- Для аутентификации реализовано получение Bearer Token для зарегистрированных пользователей
- Заявленные требования по обработке и хранении сообщений выполнены
- Код покрыт тестами на 92%
- Проект упакован в докер образ и выложен по [адресу](https://hub.docker.com/r/ath31st/door_next_docker) 

