package CoderCaesar;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите режим работы: \n" +
                    "1 - зашифровать\n" +
                    "2 - расшифровать\n" +
                    "3 - применить метод перебора ключей\n" +
                    "4 - применить метод синтаксического анализа\n" +
                    "5 - выход из программы");
            String string = scanner.nextLine();
            switch (string) {
                case ("1") -> new Coder().coding();
                case ("2") -> new Decoder().decode();
                case ("3") -> new BrutalForce().brutalForce();
                case ("4") -> new StatAnalyzer().statAnalysis();
            }
            if (string.equalsIgnoreCase("5")) {
                break;
            }
        }
    }
}
