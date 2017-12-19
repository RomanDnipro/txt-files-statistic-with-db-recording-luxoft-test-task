import java.util.Arrays;

public class LineStatisticItem {

    private String line;
    private String[] words;
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private double averageWordLength;

    public LineStatisticItem(String line) {
        this.line = line;
        words = LineStatisticUtility.splitWordsByWhitespaces(line);
        longestWord = LineStatisticUtility.findLongestWord(words);
        shortestWord = LineStatisticUtility.findShortestWord(words);
        lineLength = line.length();
        averageWordLength = LineStatisticUtility.calcAverageWordsLength(words);
    }

    @Override
    public String toString() {
        return "\n\tstatistic of line: '" + line + '\'' + '\n' +
                "\t{" + '\n' +
                "\t\twords=" + Arrays.toString(words) + '\n' +
                "\t\t, longestWord='" + longestWord + '\'' + '\n' +
                "\t\t, shortestWord='" + shortestWord + '\'' + '\n' +
                "\t\t, lineLength=" + lineLength + '\n' +
                "\t\t, averageWordLength=" + averageWordLength + '\n' +
                "\t}" + '\n';
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }
}