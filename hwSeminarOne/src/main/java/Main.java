import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw seminar one");
        //Напишите программу, которая использует Stream API для обработки списка чисел.
        //Программа должна вывести на экран среднее значение всех четных чисел в списке.

        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(getAverageOfAllEvenNumbers(integerList));

    }

    public static double getAverageOfAllEvenNumbers(List<Integer> list) {
        return list.stream().filter(n -> n % 2 == 0).mapToDouble(n -> (double) n).average().orElse(0);
    }
}
