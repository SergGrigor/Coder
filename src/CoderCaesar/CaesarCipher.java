package CoderCaesar;

import java.nio.file.Path;

public class CaesarCipher {
    private static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_+|[];',./{}:\"\\<>?!№ ";

    public String encrypt(String massage, int key) {
        StringBuilder result = new StringBuilder();
        for (char symbol : massage.toCharArray()) {
            int originalPosition = alphabet.indexOf(symbol);
            char newSymbol = 0;
            if (originalPosition >= 0) {
                int newPosition = originalPosition + key;
                if (newPosition > (alphabet.length() - 1)) {
                    newPosition = newPosition - alphabet.length();
                    newSymbol = alphabet.charAt(newPosition);
                } else if (newPosition < 0) {
                    newPosition = alphabet.length() + newPosition;
                    newSymbol = alphabet.charAt(newPosition);
                } else {
                    newSymbol = alphabet.charAt(newPosition);
                }
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
        String encrypt = caesarCipher.encrypt("русский алфавит прописные буквы строка", 161);
        System.out.println(encrypt);
        String deEncrypt = caesarCipher.deEncrypt(encrypt, 161);
        System.out.println(deEncrypt);
        System.out.println(alphabet.length());
    }
}
