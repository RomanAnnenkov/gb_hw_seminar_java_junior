import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    // Используя Reflection API, напишите программу,
    // которая выводит на экран все методы класса String.
    public static void main(String[] args) {
        Class<String> stringClass = String.class;
        Method[] methods = stringClass.getDeclaredMethods();

        System.out.println("Список методов класса String: ");
        Arrays.stream(methods).forEach(System.out::println);
    }
}
