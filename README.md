# Compare phones app

Приложение для сравнения характеристик мобильных устройств

![](comparephones.gif)

Минимальная версия андроид, необходимая для установки: **4.4 KitKat**

#### Архитектура:
MVP, Clean Architecture


#### Используемые технологии:
Kotlin

Retrofit

SQLite (Room)

RxJava 2, RxBinding

Dagger 2

JUnit + Mockito


#### TODO
- Список недавних устройств при выборе устройства и на экране Recent
- экран Devices с оффлайн списком устройств по брендам
- возможность одной кнопкой загрузить все девайсы в хранилище
- получать фотографию устройства с внешнего источника и выводить на экран (апи, которое используется сейчас выдает только характеристики устройств без фотографий)



**Перед сборкой проекта необходимо в gradle.properties указать токен к fonoapi:**

```
THE_FONO_API_KEY = "your_token"
```

Получить его можно тут: https://fonoapi.freshpixl.com/
