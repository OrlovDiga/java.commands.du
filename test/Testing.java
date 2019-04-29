import org.junit.Assert;
import org.junit.Test;

public class Testing {
    private FileParser experimental = new FileParser();


    private void addFiles(FileParser fileParser) {
        fileParser.addFile("/Users/macbook/Desktop/lab-4-master");
        fileParser.addFile("/Users/macbook/Desktop/lab-3");
        fileParser.addFile("/Users/macbook/Desktop/javafx-sdk-11.0.2");
        fileParser.addFile("/Users/macbook/Desktop/JHelp_1.0");
        fileParser.addFile("/Users/macbook/Desktop/фотки");
    }


    @Test
    public void testFirstPositive() {
        //Добавляем файлы:
        addFiles(experimental);
        //Метод суммарного размера всех файлов:
        experimental.setCatalog();
        //Метод, реализующий человеко-читаемый формат:
        experimental.setNotation();

        Assert.assertEquals(experimental.conclusionValues(), "Каталог файлов" + " = " + 1 + " " + "KB");
    }


    @Test
    public void testSecondPositive() {
        addFiles(experimental);
        experimental.setNotation();
        //Перевод системы счисления с 1024 на 1000:
        experimental.setNumberSystem();

        Assert.assertEquals(experimental.conclusionValues(), "/Users/macbook/Desktop/lab-4-master = 160 B\n" +
                "/Users/macbook/Desktop/lab-3 = 256 B\n" +
                "/Users/macbook/Desktop/javafx-sdk-11.0.2 = 160 B\n" +
                "/Users/macbook/Desktop/JHelp_1.0 = 224 B\n" +
                "/Users/macbook/Desktop/фотки = 256 B");
    }


    @Test
    public void testThirdPositive() {
        addFiles(experimental);
        experimental.setNumberSystem();
        experimental.setCatalog();

        Assert.assertEquals(experimental.conclusionValues(), "Каталог файлов = 1056");
    }


    @Test
    public void testFourthPositive() {
        addFiles(experimental);
        experimental.setCatalog();
        experimental.setNumberSystem();
        experimental.setNotation();

        Assert.assertEquals(experimental.conclusionValues(), "Каталог файлов = 1 KB");
    }


    @Test
    public void testFifthPositive() {
        addFiles(experimental);
        experimental.setNotation();

        Assert.assertEquals(experimental.conclusionValues(), "/Users/macbook/Desktop/lab-4-master = 160 B\n" +
                "/Users/macbook/Desktop/lab-3 = 256 B\n" +
                "/Users/macbook/Desktop/javafx-sdk-11.0.2 = 160 B\n" +
                "/Users/macbook/Desktop/JHelp_1.0 = 224 B\n" +
                "/Users/macbook/Desktop/фотки = 256 B");
    }


    @Test
    public void testSixthPositive() {
        addFiles(experimental);
        experimental.setCatalog();

        Assert.assertEquals(experimental.conclusionValues(), "Каталог файлов = 1056");
    }


    @Test
    public void testSeventhPositive() {
        addFiles(experimental);
        experimental.setNumberSystem();

        Assert.assertEquals(experimental.conclusionValues(), "/Users/macbook/Desktop/lab-4-master = 160 \n" +
                "/Users/macbook/Desktop/lab-3 = 256 \n" +
                "/Users/macbook/Desktop/javafx-sdk-11.0.2 = 160 \n" +
                "/Users/macbook/Desktop/JHelp_1.0 = 224 \n" +
                "/Users/macbook/Desktop/фотки = 256");
    }


    //Решил сделать проверку выброса ошибки таким образом:
    @Test(expected = Error.class)
    public void testOneNegative() {
        experimental.addFile("noName.txt");
    }
}