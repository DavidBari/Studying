package json;

import java.util.List;

public class ListStatistics {
    List<Statistics> list;

    public List<Statistics> getList() {
        return list;
    }

    public void setList(List<Statistics> list) {
        this.list = list;
    }

    public ListStatistics(List<Statistics> list) {
        this.list = list;
    }
}
