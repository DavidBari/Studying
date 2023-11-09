package servizi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScriviFile {

    private static FileWriter writer = null;
    private static Map<Key, List<String>> mappa = null;

    /**
     * Data una Mappa e un path in input, crea dei file txt ramificati in base alla mappa data, a partire dal path dato
     * @param map
     * @param path
     */
    public static void scriviFile(Map<Key, List<String>> map, String path) {
        mappa = map;
        List<Key> keys = new ArrayList<>(map.keySet());

        //ciclo sulle chiavi della mappa
        for(int i=0; i<keys.size();i++){

            //per ogni matricola cerco se esiste la cartella con lo stesso nome sennò la creo
            File directory = new File(path+"\\"+keys.get(i).getMatricola());
            if (!directory.exists()){
                directory.mkdirs();
            }

            try{

                //creo un file per ogni identificativo
                writer = new FileWriter(directory + "/"+keys.get(i).getIdentificativo()+".txt");

                //scrivo la prima parte del file con il primo identificativo trovato
                writeRiga(keys.get(i));

                //ciclo la lista fino alla fine e cerco se esistono due key da associare
                for(int j=i+1;j<keys.size();j++){
                    if(keys.get(i).getMatricola().equals(keys.get(j).getMatricola()) && keys.get(i).getIdentificativo().equals(keys.get(j).getIdentificativo())) {
                        writeRiga(keys.get(j));
                        i=j;
                    }

                    //blocco subito il ciclo perchè la mappa è ordinata e se non trova un associazione subito dopo è inutile continuare
                    else
                        break;
                }
                writer.close();
            } catch (IOException e) {
                System.err.println("Errore durante la creazione del file: " + e.getMessage());
            }
        }
    }

    //funzione che dato in input una key, scrive la riga con il progressivo e le righe con il contenuto della lista associata alla key
    private static void writeRiga(Key key){
        try {

            //scrivo riga formata da "(progressivo)"
            writer.write("(" + key.getProgressivo() + ")");
            writer.write("\n");

            //ciclo sulla lista e per ogni stringa scrivo una riga
            for (String temp : mappa.get(key)) {
                writer.write(temp);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file: " + e.getMessage());
        }
    }


}
