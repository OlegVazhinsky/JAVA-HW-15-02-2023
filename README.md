Задача:

Сформируйте часть WHERE sql-запроса, используя StringBuilder.
Данные для фильтрации приведены в виде json строки.

Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
Если значение null, то параметр не должен попадать в запрос.

Sql-запроса "select * from students where ...".