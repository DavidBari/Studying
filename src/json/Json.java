package json;

public class Json {
    String matricola;
    FilesJson files;

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public FilesJson getFiles() {
        return files;
    }

    public void setFiles(FilesJson files) {
        this.files = files;
    }

    public Json(String matricola, FilesJson files) {
        this.matricola = matricola;
        this.files = files;
    }
}
