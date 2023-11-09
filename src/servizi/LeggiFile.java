package servizi;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeggiFile {
    // Contiene la mappa dei file letti
    static Map<Key,List<String>> files = null;
    // valore di key in base al ultimo check
    private static Key key = null;

    /**
     * Dato un path, restituisce una mappa ordinata del contenuto dei file trovati
     * @param path
     * @return Mappa ordinatas
     */
    public static Map<Key,List<String>> findAllFilesInFolder(String path){
        files = new HashMap<>();
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                conservaRighe(path+"\\"+file.getName());
            }
        }
        ordinaMappa();
        return files;
    }

    //funzione che controlla se la riga matcha con la struttura "-> {L} LedZeppelin 2"
    private static boolean checkLine (String line){
        String regex = "^-> \\{(\\w+)\\} (\\w+) (\\d+)$";
        Pattern pt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher match = pt.matcher(line);
        if(match.find()){
            key = new Key();
            key.setIdentificativo(match.group(2));
            key.setMatricola(match.group(1));
            key.setProgressivo(Integer.valueOf(match.group(3)));
            return true;
        }
        return false;
    }

    //funzione che legge i file e salva il contenuto in una mappa
    private static void conservaRighe(String file){
        List<String> stb = new ArrayList<>();
        Key tempKey = null;
        try(Scanner reader = new Scanner(new File(file))){
            String line = "";
            while(reader.hasNextLine()) {
                line = reader.nextLine();

                //controllo se la riga è una riga di inizio sequenza
                if (checkLine(line)){

                    //se la lista delle righe della sequenza è > 0 significa che la sequenza conteneva almeno una riga e quindi ha senso salvarla nella mappa
                    if(stb.size() > 0){
                        files.put(tempKey, stb);
                        stb = new ArrayList<>();
                    }

                    tempKey = key;
                }
                else{
                    stb.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Errore durante la cre: " + e.getMessage());
    }
        if(stb.size() > 0){
            files.put(tempKey, stb);
        }
    }

    //funzione di ordinamento della mappa
    private static void ordinaMappa(){
        if(files != null){
            List<String> stb = null;
            List<Key> chiavi = new ArrayList<>(files.keySet());
            Collections.sort(chiavi);

            Map<Key, List<String>> mappaOrdinata = new LinkedHashMap<>();
            for (Key chiave : chiavi) {
                mappaOrdinata.put(chiave, files.get(chiave));
            }

            files = mappaOrdinata;
        }
    }

}
