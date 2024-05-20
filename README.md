Middle service for telegram-bot
========================
Учебный проект в рамках GPBF. Middle service принимает запросы от telegram-бота, выполняет валидацию и бизнес логику, 
маршрутизирует запросы в "банк"(сервис выступающий в качестве АБС).

## AS IS
[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=Проект+в+процессе+разработки...)](https://git.io/typing-svg)

## TO BE
Предполагается, взаимодействие будет происходить таким образом:

```plantuml
@startuml
actor Client
participant TelegramBot
participant MiddleService
participant ABS
Client -> TelegramBot: HTTP-запрос
activate TelegramBot
TelegramBot -> MiddleService: HTTP-запрос
activate MiddleService
alt Валидация прошла успешно
MiddleService -> ABS: HTTP-запрос
activate ABS
ABS -> MiddleService: HTTP-ответ с данными
deactivate ABS
MiddleService -> TelegramBot: HTTP-ответ с данными
TelegramBot -> Client: HTTP-ответ с данными
else Валидация не прошла
MiddleService -> TelegramBot: HTTP-ответ c кодом 200, ошибка указана в payload
TelegramBot -> Client: HTTP-ответ c кодом 200, ошибка указана в payload 
end
@enduml
```

### Для запуска проекта:

Пока нечего запускать

### Ссылка на Swagger:

Пока нечего документировать