package CoderCaesar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StatAnalyzer {

    public void statAnalysis() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к зашифрованному файлу");
        String encryptedFile = scanner.nextLine();
        System.out.println("Введите путь к файлу для набора статистики");
        String statFile = scanner.nextLine();
        System.out.println("Введите путь файла с расшифрованным текстом");
        String notEncryptedFile = scanner.nextLine();
        Map<Character, Integer> encrypted = new HashMap<>();
        Map<Character, Integer> statistic = new HashMap<>();
        Map<Character, Character> deEncrypted = new HashMap<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(encryptedFile));
             BufferedReader statBuffReader = Files.newBufferedReader(Path.of(statFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(notEncryptedFile))) {
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
            while (statBuffReader.ready()) {
                String string = statBuffReader.readLine();
                char[] chars = string.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (!statistic.containsKey(chars[i])) {
                        statistic.put(chars[i], 1);
                    } else {
                        statistic.put(chars[i], statistic.get(chars[i]) + 1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
