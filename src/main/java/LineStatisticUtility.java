import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public class LineStatisticUtility {

    public static String findLongestWord(String line) {
        return findLongestWord(splitWordsByWhitespaces(line));
    }

    public static String findLongestWord(String[] words) {
        return Arrays.stream(/*line.trim().split("\\s+")*/ words).max(Comparator.comparingInt(String::length)).orElse(null);
    }

    public static String findShortestWord(String line) {
        return findShortestWord(splitWordsByWhitespaces(line));
    }

    public static String[] splitWordsByWhitespaces(String line) {
        //если добавить trim(), то пустая строка будет "",
        //если не добавлять - null, и соответсвенно - NullPointerException
        //т.к. orElse(null), а не orElse("")
        //подумать, что делать: кидать исключение, если да, то где и как обрабатывать...
        return line.trim().split("\\s+");
    }

    public static double calcAverageWordsLength(String line) {
        return calcAverageWordsLength(line, 1);
    }

    public static double calcAverageWordsLength(String line, int scale) {
        return calcAverageWordsLength(splitWordsByWhitespaces(line), scale);
    }

    public static String findShortestWord(String[] words) {
        return Arrays.stream(/*line.trim().split("\\s+")*/  words).min(Comparator.comparingInt(String::length)).orElse(null);
    }

    public static double calcAverageWordsLength(String[] words, int scale) {
        double count = 0;
        for (String word : words) {
            count += word.length();
        }
        return BigDecimal.valueOf(count / words.length).setScale(scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//        return count / words.length;    }
    }

    public static double calcAverageWordsLength(String[] words) {
        return calcAverageWordsLength(words, 1);
    }
}
