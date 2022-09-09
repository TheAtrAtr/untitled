import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("""
                Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.
                Данные передаются в одну строку. Калькулятор умеет работать как с арабскими, так и с римскими числами.
                Калькулятор принимает на вход числа от 1 до 10 включительно, не более. Калькулятор умеет работать только с целыми числами.""");

        System.out.println("Input:");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String result = calc(scanner.nextLine());
                System.out.println("Output:");
                System.out.println(result);
            } catch (Exception e) {
                break;
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] arabic = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        ArrayList<String> romanDictonary = new ArrayList<>();
        ArrayList<String> arabicDictonary = new ArrayList<>();
        arabicDictonary.addAll(Arrays.asList(arabic));
        romanDictonary.addAll(Arrays.asList(roman));

        String[] inputArray = input.split(" ");
        if (inputArray.length == 1) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        }
        if (inputArray.length != 3 && inputArray.length != 1) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (romanDictonary.contains(inputArray[0]) && arabicDictonary.contains(inputArray[2]) || romanDictonary.contains(inputArray[2]) && arabicDictonary.contains(inputArray[0])) {
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            throw new Exception();
        }
        if (romanDictonary.contains(inputArray[0]) && romanDictonary.contains(inputArray[2])) {

            int a = 1 + romanDictonary.indexOf(inputArray[0]);
            int b = 1 + romanDictonary.indexOf(inputArray[2]);
            int z = 0;
            if (inputArray[1].equals("+"))
                z = a + b;
            else if (inputArray[1].equals("-"))
                z = a - b;
            else if (inputArray[1].equals("*"))
                z = a * b;
            else if (inputArray[1].equals("/"))
                z = a / b;
            if (z < 1) {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел и нуля");
            }
            return toRoman(z);
        } else if (arabicDictonary.contains(inputArray[0]) && arabicDictonary.contains(inputArray[2])) {

            int a = Integer.parseInt(inputArray[0]);
            int b = Integer.parseInt(inputArray[2]);
            if (inputArray[1].equals("+"))
                return String.valueOf(a + b);
            else if (inputArray[1].equals("-"))
                return String.valueOf(a - b);
            else if (inputArray[1].equals("*"))
                return String.valueOf(a * b);
            else if (inputArray[1].equals("/"))
                return String.valueOf(a / b);
        }

        return "на вход принимаются числа от 1 до 10 включительно, не более";
    }

    private final static TreeMap<Integer, String> TREE_MAP = new TreeMap<Integer, String>();
    static {
        TREE_MAP.put(1000, "M");
        TREE_MAP.put(900, "CM");
        TREE_MAP.put(500, "D");
        TREE_MAP.put(400, "CD");
        TREE_MAP.put(100, "C");
        TREE_MAP.put(90, "XC");
        TREE_MAP.put(50, "L");
        TREE_MAP.put(40, "XL");
        TREE_MAP.put(10, "X");
        TREE_MAP.put(9, "IX");
        TREE_MAP.put(5, "V");
        TREE_MAP.put(4, "IV");
        TREE_MAP.put(1, "I");
    }
    public final static String toRoman(int number) {
        int l = TREE_MAP.floorKey(number);
        if (number == l) {
            return TREE_MAP.get(number);
        }
        return TREE_MAP.get(l) + toRoman(number - l);
    }
}