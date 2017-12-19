import java.io.BufferedReader;
import java.io.Reader;

public class OnlyTxtFileBufferedReader extends BufferedReader{

    public OnlyTxtFileBufferedReader(Reader in) {
        super(in);
    }
}
