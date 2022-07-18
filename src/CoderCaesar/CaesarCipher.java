package CoderCaesar;

public class CaesarCipher {
    private static final String ALPHABET_PART_ONE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":!? –+-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789\u00A0\u2026";
    private static final String ALPHABET_PART_TWO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":!? –+-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789\u00A0\u2026";
    private static final String ALPHABET = ALPHABET_PART_ONE + ALPHABET_PART_TWO;

    public int alphabetLength () {
        return ALPHABET.length() / 2;
    }

    public String encrypt(String massage, int key) {
        StringBuilder result = new StringBuilder();
        for (char symbol : massage.toCharArray()) {
            int originalPosition = ALPHABET.indexOf(symbol);
            int newPosition;
            char newSymbol = 0;
            if (originalPosition >= 0) {
                if (key >= 0) {
                    newPosition = (originalPosition + key) % (alphabetLength());
                } else {
                    int newKey = key % (ALPHABET.length() / 2);
                    newPosition = (originalPosition + (alphabetLength()) + newKey) % ALPHABET.length();
                }
                newSymbol = ALPHABET.charAt(newPosition);
            }
            result.append(newSymbol);
        }
        return result.toString();
    }
    public String deEncrypt(String massage, int key) {
        return encrypt(massage, -1 * key);
    }
}
