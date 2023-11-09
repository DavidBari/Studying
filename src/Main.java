import servizi.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static final String PATH = "C:\\Progetti\\Test\\Files";
    public static final String PATHOUT = "C:\\Progetti\\Test\\Files\\Output";
    public static void main(String[] args) throws IOException {

        List<String> temp = new ArrayList<>();

        temp.add("12");
        temp.add("AF");
        temp.add("GG");
        temp.add("45");
        temp.add("");
        temp.add("GRGR");
        temp.add("RFDE");

        temp = temp.stream()
                .map(p -> p.isEmpty() ? "123" : p)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(temp);

        Map<String, List<String>> test = new HashMap<>();



//        Map<Key, List<String>> map =  LeggiFile.findAllFilesInFolder(PATH);
//        ScriviFile.scriviFile(map,PATHOUT);
//        InvocaServizio.invocaServizi(PATHOUT);

    }
}