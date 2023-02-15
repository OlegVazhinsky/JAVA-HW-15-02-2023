/*

Задача:

Сформируйте часть WHERE sql-запроса, используя StringBuilder.
Данные для фильтрации приведены в виде json строки.

Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
Если значение null, то параметр не должен попадать в запрос.

Sql-запроса "select * from students where ...".

*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task1{
    public static void main(String[] args) throws IOException{
        parseJsonFile("request.json");
    }

    public static void writeToFile(String filePath, String line) throws IOException{
        // связать переменную с файлом
        File file = new File(filePath);
        // если файл не создан - создать его
        if (file.createNewFile()){
            System.out.println("Файл " + filePath + " создан.");
        }
        // дописать данные в файл
        FileWriter writer = new FileWriter(file, true);
        writer.write(line + "\n");
        // закрыть файл
        writer.close();
    }

    public static void parseJsonFile(String filePath) throws IOException{
        // связать переменную с файлом
        File file = new File(filePath);
        // проверить есть ли файл и не пуст ли он
        if (file.exists() & file.length() > 0){
            // считать в reader файл. Путь file.
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // переменная для хранения текущей строки
            String line;
            // массив для хранения считываемых значений
            String[] tmpArray = new String[2];
            // переменная для выходной строки запроса
            StringBuilder outString = new StringBuilder();
            outString.append("select * from students where ");
            // считать строку из файла и если она не пустая
            while ((line = reader.readLine()) != null) {
                // убрать из строки символы: " ", "[", "]", "{", "}", ","
                line = line.replaceAll("\s|\\[|\\]|\\}|\\{|\n|\\,", "");
                // если строка не пустая, т.е. есть что записать
                if (line.length() > 0){
                    // убрать из строки символ "
                    line = line.replaceAll("\"", "");
                    // сформировать массив из ключа и значения
                    tmpArray = line.split(":");
                    // если значение отлично от null, дописать в строку запроса
                    if (!tmpArray[1].equals("null")){
                        outString.append(tmpArray[0] + " = '" + tmpArray[1] + "' and ");
                    }
                }
                // если строка файла пуста
                else {
                    // если строка запроса уже сформирована
                    if (outString.length() > 29){
                        // убрать последние 5 символов " and "
                        outString.setLength(outString.length() - 5);
                        // записать строку в файл
                        writeToFile("task1-request.txt", outString.toString());
                        // очистить строку
                        outString.setLength(0);
                        // сформировать ноое начало строки
                        outString.append("select * from students where ");
                    }
                }
            }
            // закрыть файл
            reader.close();
        }
        // если файла нет или он пуст вывести сообщение
        else {
            System.out.println("Файл " + filePath + " не существует или пуст.");
        }
    }

}