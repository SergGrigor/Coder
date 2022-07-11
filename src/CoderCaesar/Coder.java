package CoderCaesar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Coder {

    public void coding () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу для его зашифровки");
        String pathNotEncryptedFile = scanner.nextLine();
        System.out.println("Введите ключ шифрования");
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите путь к файлу с зашифрованным текстом");
        String pathEncryptedFile = scanner.nextLine();
        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathNotEncryptedFile));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(pathEncryptedFile))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptString = caesarCipher.encrypt(string, key);
                writer.write(encryptString + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Содержимое файла зашифровано");
    }
}
