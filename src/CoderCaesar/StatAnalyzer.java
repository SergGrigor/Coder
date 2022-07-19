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
        CaesarCipher caesarCipher = new CaesarCipher();
        Map<Character, Integer> mapEncrypted = new HashMap<>();
        Map<Character, Integer> mapStatistic = new HashMap<>();
        Map<Character, Character> mapDeEncrypted = new HashMap<>();
        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(fillMapValues(encryptedFile, mapEncrypted));
        List<Map.Entry<Character, Integer>> listStat = mapToList(fillMapValues(statFile, mapStatistic));
        if (listStat.size() > listEncrypted.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                mapDeEncrypted.put(listEncrypted.get(i).getKey(), listStat.get(i).getKey());
            }
            try(BufferedReader bufferedReader = Files.newBufferedReader(Path.of(encryptedFile));
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(notEncryptedFile))) {
                int cipherKey = caesarCipher.getALPHABET().indexOf(listEncrypted.get(0).getKey()) -
                                caesarCipher.getALPHABET().indexOf(listStat.get(0).getKey());
                while (bufferedReader.ready()) {
                    /*
                    String string = bufferedReader.readLine();
                    char[] chars = string.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        char deEncryptedChar = mapDeEncrypted.get(chars[i]);
                        bufferedWriter.write(deEncryptedChar);
                    }
                     */
                    bufferedWriter.write(caesarCipher.deEncrypt(bufferedReader.readLine(), cipherKey) + System.lineSeparator());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Размер файла статистики должен быть больше, чем размер файла с зашифрованным текстом.");
        }
    }

    private Map<Character, Integer> fillMapValues(String path, Map<Character, Integer> map) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                for (char aChar : string.toCharArray()) {
                    if (!map.containsKey(aChar)) {
                        map.put(aChar, 1);
                    } else {
                        map.put(aChar, map.get(aChar) + 1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return map;
    }
    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        List <Map.Entry<Character, Integer>> list = new ArrayList<>(set);
//        Comparator <Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
//                return o1.getValue() - o2.getValue();
//            }
//        };
        //Comparator <Map.Entry<Character, Integer>> comparator = (o1, o2) -> o1.getValue() - o2.getValue();
        //Comparator <Map.Entry<Character, Integer>> comparator = Comparator.comparingInt(Map.Entry::getValue);
        Comparator <Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();
        list.sort(comparator.reversed());
        return list;
    }
}
