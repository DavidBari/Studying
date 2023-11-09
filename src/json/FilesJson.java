package json;

import java.util.List;

public class FilesJson {
    String id;
    String path;
    List<Statistics> statistics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public FilesJson(String id, String path, List<Statistics> statistics) {
        this.id = id;
        this.path = path;
        this.statistics = statistics;
    }
}
