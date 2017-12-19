import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static final String USER_NAME = "root";
    static final String PASSWORD = "root";
    static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";

    static final String INSERT_TO_PATH_BY_USERS_QUERY_TABLE = "INSERT INTO path_by_users_query (path_quary, json_list_of_txt_files) VALUES (?, ?)";
    static final String INSERT_TO_TXT_FILE_LIST_TABLE = "INSERT INTO txt_files_list (path_to_folder, list_of_txt_files) VALUES (?, ?)";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD)) {


            System.out.println("Введите путь к директории, или имя .txt-файла, например: D:\\test2.txt, для статистики:");

            HashMap<File, FileStatisticForEachLine> filesStatisticMap = new HashMap();
            String fileName = "D:\\test2.txt";
            File file = new File(fileName);
            ArrayList<File> filesList = new ArrayList<>();
            Helper.getAllTxtFilesFromFolder(file, filesList);
            DBWorker dbWorker = new DBWorker();


            for (File f : filesList) {
                filesStatisticMap.put(f, new FileStatisticForEachLine(f));
            }

            try {
                //ДОБАВЛЯЕМ В ТАБЛИЦУ path_by_users_query ЗАПРОС ПОЛЬЗОВАТЕЛЯ И
                //JSON ПРЕДСТАВЛЕНИЕ СПИСКА TXT-ФАЙЛОВ ПО ЭТОМУ ЗАПРОСУ
                dbWorker.addRecordToPathByUsersQueryTable(connection, fileName, filesList);

                //ДОБАВЛЯЕМ В ТАБЛИЦУ txt_files_list ПУТИ К TXT-ФАЙЛАМ И
                //JSON ПРЕДСТАВЛЕНИЯ ОБЪЕКТОВ FileStatisticForEachLine
                // ДЛЯ КАЖДОГО ФАЙЛА
                dbWorker.addRecordToTxtFileListTable(connection, filesStatisticMap);
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062){
                    System.out.println("-----соответствующая запись уже существует в базе данных-----");
                } else {
                    e.printStackTrace();
                }
            }

            for (FileStatisticForEachLine value : filesStatisticMap.values()) {
                System.out.println(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        for (Map.Entry<File, FileStatisticForEachLine> entry : filesStatisticMap.entrySet()) {
//            System.out.println(entry.getKey().getName() + " :");
//            for (LineStatisticItem item : entry.getValue().getLineStatisticItems()) {
//                System.out.println("Line:  " + item.getLine());
//                System.out.println("LongestWord:  " + item.getLongestWord());
//                System.out.println("ShortestWord:  " + item.getShortestWord());
//                System.out.println("LineLength:  " + item.getLineLength());
//                System.out.println("AverageWordLength:  " + item.getAverageWordLength());
//                System.out.println();
//            }
//        }
//        for (File f : filesList) {
//            FileStatisticForEachLine fileStatistic = new FileStatisticForEachLine(f);
//            fileStatisticForEachLines.add(fileStatistic);
//            for (String line : Files.readAllLines(f.toPath())) {
//                fileStatistic.getLineStatisticItems().add(new LineStatisticItem(line));
//            }
//        }
    }

//        try {
//            BufferedReader bufferedReader = new BufferedReader(new TxtFileReader(file));
//        } catch (FileIsDirectoryException | IOException | UnsupportedFileNameException e) {
//            e.printStackTrace();
//        }
//        try {
//            FileReader reader = new TxtFileReader(new File(fileName));
//        } catch (FileIsDirectoryException | IOException | UnsupportedFileNameException e) {
//            e.printStackTrace();
//        }


//        try (BufferedReader readerForFileName = new BufferedReader(new InputStreamReader(System.in))) {
//            try (BufferedReader readerForLines = new BufferedReader(new TxtFileReader(fileName))) {
//
//                String line;
//                while ((line = readerForLines.readLine()) != null) {
//                    filesList.add(line);
//                }
//            }
//        } catch (IOException | UnsupportedFileNameException e) {
//            e.printStackTrace();
//        }
//
//        for (String s : filesList) {
//            String longestWord = LineStatisticUtility.findLongestWord(s);
//            String shortestWord = LineStatisticUtility.findShortestWord(s);
//
//            try {
//                if (longestWord.isEmpty()) {
//                    System.out.println("empty");
//                } else {
//                    System.out.println("\"" + longestWord + "\"\t" + LineStatisticUtility.calcAverageWordsLength(s) + "\t|" + "\"" + shortestWord + "\"");
//                }
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//        }


    public void folderMethod(File folder, ArrayList<File> fileList) {
        FilenameFilter txtFileFilter = new TxtFilenameFilter();
        //Passing txtFileFilter to listFiles() folderMethod to retrieve only txt files
        File[] files = folder.listFiles(txtFileFilter);
        for (File file : files) {
            fileList.add(file);
//            System.out.println(file.getName());
        }
    }


}

/**
 * 1 получить имя файла/папки
 * 2 создать new File(fileName)
 * 3 создать txtFilesList, где будут хранится все txt.-файлы
 * (или один, если юзер ввёл путь файла, а не папки)
 * 4
 * getAllTxtFilesFromFolder(){
 * - isFile()
 * true - fileMethod():
 * isTxtFile()
 * true - txtFiles.add()
 * else - folderMethod():
 * <p>
 * }
 */
