import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    private List<TripleFilesProperty> files = new ArrayList<>();
    private String[] notation = new String[]{"B", "KB", "MB", "GB"};
    private short numberSystem = 1024;


    public void addFile(String nameFile) {
        files.add(new TripleFilesProperty(nameFile, new File(nameFile).length(), ""));

        if (new File(nameFile).length() == 0) {
            throw new Error("Файла с именем " + nameFile + " не существует.");
        }
    }


    public void setCatalog() {
        Long temp = 0L;

        for (TripleFilesProperty fileSize: files) {
            temp += fileSize.second;
        }

        files.clear();
        files.add(new TripleFilesProperty("Каталог файлов", temp, ""));
    }


    public void setNumberSystem() {
        numberSystem = 1000;
    }


    public void setNotation() {
        for( int i = 0; files.size() > i; i++) {
            Object[] tempArr = searchNumber(files.get(i).getSecond());
            files.set(i, files.get(i)).setSecond((Long)tempArr[0]);
            files.set(i, files.get(i)).setThird((String) tempArr[1]);
        }
    }


    private Object[] searchNumber(Long num) {
        Long temp = num;
        byte i = 0;

        while (temp > numberSystem && (i != 3)) {
            i++;
            temp /= numberSystem;
        }

        return new Object[]{temp, notation[i]};
    }


    public void conclusionValues() {
        for (TripleFilesProperty dataFile: files) {
            System.out.println(dataFile.first + " = " +
                               dataFile.second + " " +
                               dataFile.third);
        }
    }


    class TripleFilesProperty {
        private String first;
        private Long second;
        private String third;


        TripleFilesProperty(String first, Long second, String third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }


        Long getSecond() {
            return second;
        }


        void setSecond(Long second) {
            this.second = second;
        }


        void setThird(String third) {
            this.third = third;
        }
    }
}
