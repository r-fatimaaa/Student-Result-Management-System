package utility;

import java.io.*;
import java.util.List;

public class DataStore<T> {

    // SAVE
    public void save(String fileName, List<T> items) {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(items);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LOAD
    @SuppressWarnings("unchecked")
    public List<T> load(String fileName) {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(fileName));
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
