package servizi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.FilesJson;
import json.Json;
import json.Statistics;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InvocaServizio {
    private static String URL = "https://test.register";
    private static String matricola = null;

    /**
     * Dato un path in input, in base alla struttura delle cartelle e file, chiama un servizio HTTP POST
     * @param path
     */
    public static void invocaServizi(String path) {
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                matricola = file.getName();
                for (File subFile : file.listFiles()) {

                    //controllo se sono file txt
                    if (subFile.isFile() && subFile.getName().endsWith(".txt")) {
                        callService(subFile);
                    }
                }
            }
        }
    }


    //funzione che crea le chiamate al servizio
    private static void callService(File file){
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            String json = createJson(file);
            System.out.println(json);
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.writeBytes(json);
                wr.flush();
            }

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
        } catch (IOException e) {
            System.err.println("Errore durante la creazione del servizio: " + e.getMessage());
        }
    }

    //funzione per la generazione del Json
    private static String createJson(File file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<String> parole = new ArrayList<>();
        List<Statistics> list = new ArrayList<>();

        try(Scanner reader = new Scanner(new File(file.getPath()))) {
            String line = "";
            while (reader.hasNextLine()) {
                line = reader.nextLine();

                //controllo se non è una riga con il progressivo
                if (!matcher(line)) {

                    //splitto la riga tramite gli spazi e mi salvo quelli che non sono empy
                    String[] array = line.split(" ");
                    for (String t : array) {
                        if (!t.isEmpty()) {
                            parole.add(t);
                        }
                    }
                }
            }

            //ciclo sulla lista delle parole e conto quante volte è presente quella parola
            for(String t:parole){
                list.add(new Statistics(t, Collections.frequency(parole, t)));
            }

        } catch (FileNotFoundException e) {
            System.err.println("Errore durante la creazione del Json: " + e.getMessage());
            return null;
        }

        FilesJson files = new FilesJson(file.getName().replace(".txt",""), file.getPath(),list);
        Json obj = new Json(matricola, files);
        return gson.toJson(obj);
    }

    //funzione che controlla se la stringa matcha con la struttura "(progressivo)"
    private static boolean matcher(String line){
        String regex = "^\\(\\d+\\)$";
        Pattern pt = Pattern.compile(regex);
        Matcher match = null;
        match = pt.matcher(line);
        return match.find();
    }
}
