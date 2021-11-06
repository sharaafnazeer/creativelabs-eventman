import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String [] args) {
//        // First letter character
//        // Cant use any symbols / special characters. Excluding underscore
//        // No spaces between names
//        // Case sensitive
//        // Cant use any keywords
//        // Variable names should be understandable.
//        // first_name - underscore case
//        // firstName - camel case
//
//
//        // Statements
//
//        // 1. Conditional Statements
//        // 2. Looping Statements
//
//        // 01)
//
//        // If
//        // If else
//        // If else if / Nested if
//        // Switch
//
//        // 02)
//        // While
//        // For
//        // Do..While
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }
//
//        // Working order
//        // 1. Initialization
//        // 2. Check Condition
//        // 3. Printing / Inner Loop Execution
//        // 4. Increment / Decrement
//
//
//        int i = 0;
//        while (i < 10) {
//            System.out.println(i);
//            i++;
//        }
//
//        // Working order
//        // 1. Initialization
//        // 2. Check Condition
//        // 3. Printing / Inner Loop Execution
//        // 4. Increment / Decrement
//
//        do {
//            System.out.println(i);
//            i++;
//        }
//        while (i < 0);
//
//        // Working order
//        // 1. Initialization
////        // 2. Printing / Inner Loop Execution
////        // 3. Increment / Decrement
////        // 4. Check Condition
//
//
//        // Increment & Decrement Operators
//
//        int x = 10;
//        int y = x--;
//
//
//        System.out.println(x);
//        System.out.println(y);
//
//        // Ternary Operator
//        int a = 10;
////        String greetings= "";
////        if (a > 10) {
////            greetings = "Hi";
////        } else if (a > 20) {
////            greetings = "Hey";
////        } else {
////            greetings = "Hello";
//   //     }
////        System.out.println(greetings);
//
//
//        String greetings = a > 10 ? "Hi" : a > 20 ? "Hey" : "Hello";
//        System.out.println(greetings);


        // Arrays

//        int[] values = new int[] {10, 20, 30};
//        for (int i = 0; i < values.length; i++) {
//            System.out.println(values[i]);
//        }
//
////        values = new int[]{10, 20, 30, 40, 50};
////        for (int i = 0; i < values.length; i++) {
////            System.out.println(values[i]);
////        }
//
////        values[5] = 10;
//
//        // Lists
//
//        List<Integer> intList = new ArrayList<>();
//        intList.add(10);
//        intList.add(20);
//        intList.add(50);
//        intList.add(70);
//        intList.add(80);
//
//
//        intList.forEach(val -> {
//            System.out.println(val);
//        });
//
//        for (int i : intList) {
//            System.out.println(i);
//        }

        // OOP
        // 1. Abstraction
        // 2. Encapsulation
        // 3. Polymorphism
        // 4. Inheritance

        Undergraduate s = new Undergraduate("Nimal", "", "Colombo", 20);
        System.out.println(s.getFirstName());
        s.doExam();
    }
}
