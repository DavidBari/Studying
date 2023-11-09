import servizi.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String PATH = "C:\\Progetti\\Test\\Files";
    public static final String PATHOUT = "C:\\Progetti\\Test\\Files\\Output";
    public static void main(String[] args) throws IOException {

        


        Map<Key, List<String>> map =  LeggiFile.findAllFilesInFolder(PATH);
        ScriviFile.scriviFile(map,PATHOUT);
        InvocaServizio.invocaServizi(PATHOUT);

    }
}