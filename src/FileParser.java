import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    private List<TripleFilesProperty> files = new ArrayList<>();
    private String[] notation = new String[]{"B", "KB", "MB", "GB"};
    private short numberSystem = 1024;

    //Добавление файлов и поиск установление их размеров:
    public void addFile(String nameFile) {
        files.add(new TripleFilesProperty(nameFile, new File(nameFile).length() * (1024 / numberSystem), ""));

        if (new File(nameFile).length() == 0) {
            throw new Error("Файла с именем " + nameFile + " не существует.");
        }
    }


    //Метод, реализующий суммарный размер всех файлов:
    public void setCatalog() {
        Long temp = 0L;

        for (TripleFilesProperty fileSize : files) {
            temp += fileSize.second;
        }

        files.clear(); //Очищаем все файлы, так как мы их суммировали в отдельный объект, который добавим в след. строке
        files.add(new TripleFilesProperty("Каталог файлов", temp, ""));
    }


    //Изменение основания системы счисления с 1024 на 1000:
    public void setNumberSystem() {
        numberSystem = 1000;
    }


    //Метод, реализующий человека-читаемый формат размера:
    public void setNotation() {
        for (TripleFilesProperty file : files) {
            Object[] tempArr = searchNumber(file.getSecond());
            file.setSecond((Long) tempArr[0]);
            file.setThird((String) tempArr[1]);
        }
    }


    //Поиск нужной единицы измерения:
    private Object[] searchNumber(Long num) {
        Long temp = num;
        byte i = 0;

        while (temp > numberSystem && (i != 3)) {
            i++;
            temp /= numberSystem;
        }

        return new Object[]{temp, notation[i]};
    }


    //Вывод результата:
    public String conclusionValues() {
        StringBuilder sb = new StringBuilder();

        for (TripleFilesProperty dataFile : files) {
            sb.append(dataFile.first + " = " +
                      dataFile.second + " " +
                      dataFile.third + "\n");
        }

        return sb.toString().trim();
    }


    class TripleFilesProperty {
         String first; //Название файла.
         Long second; //Размер файла.
         String third; // Единица измерения(B, KB, MB, GB).


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
