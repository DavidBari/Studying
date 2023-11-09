package servizi;

public class Key implements Comparable<Key>{
    private String identificativo;
    private int progressivo;
    private String matricola;

    public String getIdentificativo() {
        return identificativo;
    }

    public void setIdentificativo(String identificativo) {
        this.identificativo = identificativo;
    }

    public int getProgressivo() {
        return progressivo;
    }

    public void setProgressivo(int progressivo) {
        this.progressivo = progressivo;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }


    @Override
    public int compareTo(Key o1) {
        int confrontoMatricola = this.matricola.compareTo(o1.matricola);
        int confrontoIdentificativo = this.identificativo.compareTo(o1.identificativo);
        int confrontoProgressivo = Long.compare(this.progressivo, o1.progressivo);

        if (confrontoMatricola != 0) {
            return confrontoMatricola;
        } else if (confrontoIdentificativo != 0) {
            return confrontoIdentificativo;
        } else {
            return confrontoProgressivo;
        }
    }
}
