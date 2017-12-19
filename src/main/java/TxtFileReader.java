import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TxtFileReader extends FileReader {
    public TxtFileReader(String fileName) throws UnsupportedFileNameException, IOException {
        super(fileName);
        if (!fileName.endsWith(".txt")) {
            super.close();
            throw new UnsupportedFileNameException("you should input .txt file name");
        }
    }

    public TxtFileReader(File file) throws IOException, UnsupportedFileNameException, FileIsDirectoryException {
        super(file);
        if (file.isDirectory()) {
            throw new FileIsDirectoryException();
        } else {
            if (!file.getPath().endsWith(".txt")) {
                super.close();
                throw new UnsupportedFileNameException("you should input .txt file name");
            }
        }
    }
}
