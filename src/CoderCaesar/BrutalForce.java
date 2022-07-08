package CoderCaesar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringBuilder.toString(), i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("");
    }

    private boolean isValidateText (String text) {
        boolean isValidate = false;
        text.split(" ");
        return isValidate;
    }
}
