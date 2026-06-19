package utility;

import java.util.ArrayList;

public class RecordList<T> {

    private ArrayList<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public ArrayList<T> getAll() {
        return items;
    }
}
