package e2e.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Molly", "Miller", "created manually"});
        list.add(new Object[]{"Bob", "Mr", "created 2 manually"});
        list.add(new Object[]{"Bobbi", "Mrs", "created 3 manually"});

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv.csv"))); //открытие и чтение файла + path
        String line = reader.readLine();         //само чтение по строкам
        while (line != null) {  //все последующие строки в файле
            String[] split = line.split(",");  //отделяем наши параметры от запятых
            list.add(new Object[]{split[0], split[1], split[2]});  //вытаскивает по индексу
            line = reader.readLine(); //после того как вытащили строку, чтоб мы снова ее читали, чтоб цикл продолжался
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> editWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data1.csv.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1]});
            line = reader.readLine();
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Object[]> newEdit() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Bibi", "leaves in Berlin"});
        return list.iterator();
    }

}
