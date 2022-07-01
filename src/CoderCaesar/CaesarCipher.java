package CoderCaesar;

import java.nio.file.Path;

public class CaesarCipher {
    private static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String encrypt(String massage, int key) {
        StringBuilder result = new StringBuilder();
        for (char symbol : massage.toCharArray()) {
            int originalPosition = alphabet.indexOf(symbol);
            char newSymbol = 0;
            if (originalPosition >= 0) {
                int newPosition = originalPosition + key;
                newSymbol = alphabet.charAt(newPosition);
            }
            result.append(newSymbol);
        }

        return result.toString();
    }
    public String deEncrypt(String massage, int key) {
        return encrypt(massage, -1 * key);
    }

    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        String encrypt = caesarCipher.encrypt("привет", 3);
        System.out.println(encrypt);
        String deEncrypt = caesarCipher.deEncrypt(encrypt, 3);
        System.out.println(deEncrypt);
    }
}
