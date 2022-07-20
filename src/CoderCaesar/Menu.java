package CoderCaesar;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Выберите режим работы:\s
                    1 - зашифровать
                    2 - расшифровать
                    3 - применить метод перебора ключей
                    4 - применить метод синтаксического анализа
                    5 - выход из программы""");
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
