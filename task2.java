import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*

Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

*/

public class task2 {

    public static void main(String[] args) throws SecurityException, IOException{
        int[] array = getRandomArray(10, 10);
        sortArray(array);
    }

    // метод сортировки массива и ведения логирования
    public static int[] sortArray(int[] array) throws SecurityException, IOException{
        // создать Logger
        Logger logger = Logger.getLogger(task2.class.getName());
        // создать FileHandler
        FileHandler fh = new FileHandler("task2.log", true);
        // добавить Handler к Logger
        logger.addHandler(fh);
        // задать простой формат логирования
        SimpleFormatter sf = new SimpleFormatter();
        fh.setFormatter(sf);
        // переменные для добавления массивов в лог
        StringBuilder oldArray = new StringBuilder();
        StringBuilder newArray = new StringBuilder();
        newArray.append("[");
        oldArray.append("[");
        // формирование лога об исходном массиве
        for (int i = 0; i < array.length; i++){
            oldArray.append(array[i] + ",");
        }
        oldArray.setLength(oldArray.length() - 1);
        oldArray.append("]");
        logger.log(Level.INFO, "Исходный массив = " + oldArray.toString());
        // метод сортировки пузырьком
        int tmp;
        for (int i = 0; i < array.length - 1; i++){
            for (int j = i + 1; j < array.length; j++){
                if (array[i] > array[j]){
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    // вывод сообщения о замене элементов
                    logger.log(Level.INFO, "Элемент [" + i + "] = " + array[i] + " поменен местами с элементом [" + j +"] = " + array[j]);
                }
            }
            newArray.append(array[i] + ",");
        }
        newArray.append(array[array.length - 1] + "]");
        // запись отсортированного массива в лог
        logger.log(Level.INFO, "Отсртированный массив = " + newArray.toString());
        return array;
    }

    // метод создания массива длиной arraySize случайными числами от 0 до maxRandom
    public static int[] getRandomArray(int arraySize, int maxRandom){
        // инициализация рандома
        Random random = new Random();
        int[] array = new int[arraySize];
        // заполнение массива случайными числами
        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt(maxRandom);
        }
        return array;
    }
}
