package CoderCaesar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decoder {

    public void decode () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу для его расшифровки");
        String pathEncryptedFile = scanner.nextLine();
        System.out.println("Введите ключ дешифрования");
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите путь к файлу с дешифрованным текстом");
        String pathNotEncryptedFile = scanner.nextLine();
        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncryptedFile));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(pathNotEncryptedFile))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String deEncrypted = caesarCipher.deEncrypt(string, key);
                writer.write(deEncrypted + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Содержимое файла расшифровано");
    }
}
