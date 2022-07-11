package CoderCaesar;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatAnalyzer {

    public void statAnalysis() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к зашифрованному файлу");
        String enCryptedFile = scanner.nextLine();
        System.out.println("Введите путь к файлу для набора статистики");
        String statFile = scanner.nextLine();
        System.out.println("Введите путь файла с расшифрованным текстом");
        String notEnCryptedFile = scanner.nextLine();
        Map<Character, Integer> encrypted = new HashMap<>();
        Map<Character, Integer> statistic = new HashMap<>();
        Map<Character, Character> deEncrypted = new HashMap<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(enCryptedFile))) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                char[] chars = string.toCharArray();
                for (int i = 0; i < chars.length; i++)
                    if (!encrypted.containsKey(chars[i])) {
                        encrypted.put(chars[i], 1);
                    } else {
                        encrypted.put(chars[i], encrypted.get(chars[i]) + 1);
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
