package CoderCaesar;

public class CaesarCipher {
    private static final String ALPHABET_PART_ONE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    private static final String ALPHABET_PART_TWO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    private static final String alphabet = ALPHABET_PART_ONE + ALPHABET_PART_TWO;

    public String encrypt(String massage, int key) {
        StringBuilder result = new StringBuilder();
        for (char symbol : massage.toCharArray()) {
            int originalPosition = alphabet.indexOf(symbol);
            int newPosition;
            char newSymbol = 0;
            if (originalPosition >= 0) {
                if (key >= 0) {
                    newPosition = (originalPosition + key) % (alphabet.length() / 2);
                } else {
                    int newKey = key % (alphabet.length() / 2);
                    newPosition = (originalPosition + (alphabet.length() / 2)  + newKey) % alphabet.length();
                }
                newSymbol = alphabet.charAt(newPosition);
            }
            result.append(newSymbol);
        }
        return result.toString();
    }
    public String deEncrypt(String massage, int key) {
        return encrypt(massage, -1 * key);
    }
}
