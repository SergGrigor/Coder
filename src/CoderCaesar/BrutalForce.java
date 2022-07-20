package CoderCaesar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BrutalForce {

    public void brutalForce() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь файла для расшифровки");
        String pathEncryptedFile = scanner.nextLine();
        System.out.println("Введите путь для расшифрованного файла");
        String pathNotEncryptedFile = scanner.nextLine();
        CaesarCipher caesarCipher = new CaesarCipher();
        try (var reader = Files.newBufferedReader(Path.of(pathEncryptedFile));
             var writer = Files.newBufferedWriter(Path.of(pathNotEncryptedFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            List<String> listStrings = new ArrayList<>();
            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
                listStrings.add(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringBuilder.toString(), i);
                if (isValidateText(deEncrypt)) {
                    for (String string: listStrings) {
                        writer.write(caesarCipher.deEncrypt(string, i) + System.lineSeparator());
                    }
                    System.out.println("Текст расшифрован. Ключ = " + i);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("");
    }

    private boolean isValidateText (String text) {
        boolean isValidate = false;
        int indexStart = new Random().nextInt(text.length() / 2);
        int indexEnd = indexStart + (int) Math.sqrt(text.length());
        String substring = text.substring(indexStart, indexEnd);
        String[] words = substring.split(" ");
        for (String word: words) {
            if (word.length() > 24) {
                return false;
            }
        }
        if (substring.contains(". ") || substring.contains(", ") || substring.contains("! ") || substring.contains("? ")) {
            isValidate = true;
        }
        while (isValidate) {
            System.out.println(substring);
            System.out.println("Текст корректно расшифрован? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("да")) {
                return true;
            } else if (answer.equalsIgnoreCase("нет")) {
                isValidate = false;
            } else {
                System.out.println("Некорректный выбор. Выберите только да или нет");
            }
        }
        return isValidate;
    }
}
