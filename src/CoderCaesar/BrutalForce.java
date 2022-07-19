package CoderCaesar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class BrutalForce {

    public void brutalForce() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь файла для расшифровки");
        String pathEncryptedFile = scanner.nextLine();
        System.out.println("Введите путь для расшифрованного файла");
        String pathNotEncryptedFile = scanner.nextLine();
        CaesarCipher caesarCipher = new CaesarCipher();
        try (//var reader = Files.newBufferedReader(Path.of(pathEncryptedFile));
             var writer = Files.newBufferedWriter(Path.of(pathNotEncryptedFile))) {
            List<String> stringList = Files.readAllLines(Path.of(pathEncryptedFile));
            /*
            //StringBuilder stringBuilder = new StringBuilder();

            while (reader.ready()) {
                String string = reader.readLine();
                stringArrayList.add(string); //stringBuilder.append(string);
            }
            */
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringList.toString(), i);//(stringBuilder.toString(), i);
                if (isValidateText(deEncrypt)) {
                    for (String string: stringList) {
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
        if (text.length() > 26) {
            String string = text;
            int indexStart = new Random().nextInt(text.length() / 2);
            int indexEnd = indexStart + (int) Math.sqrt(text.length());
            if ((indexStart - indexEnd) > 26) {
                string = text.substring(indexStart, indexEnd);
            }
            if (isWord(string) && (string.contains(". ")
                    || string.contains(", ")
                    || string.contains("! ")
                    || string.contains("? "))) {
                isValidate = answerValidate(string);
            }

        } else if (isWord(text)) {
                isValidate = answerValidate(text);
        }
        return isValidate;
    }

    private boolean answerValidate(String text) {
        while (true) {
            System.out.println(text);
            System.out.println("Текст корректно расшифрован? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("да")) {
                return true;
            } else if (answer.equalsIgnoreCase("нет")) {
                break;
            } else {
                System.out.println("Некорректный выбор. Выберите только да или нет");
            }
        }
        return false;
    }

    private  boolean isWord(String text) {
        String[] words = text.split(" ");
        for (String word : words) {
            if (word.length() > 24) {
                return false;
            }
        }
        return true;
    }
}
