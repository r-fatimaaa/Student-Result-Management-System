package gui;

import backend.Student;
import utility.DataStore;
import utility.RecordList;

import java.util.List;

public class SRMSRunner {

    public static RecordList<Student> students = new RecordList<>();
    public static DataStore<Student> store = new DataStore<>();

    private static final String FILE_NAME = "students.dat";

    public static void load() {
        List<Student> loaded = store.load(FILE_NAME);
        if (loaded != null) {
            students.getAll().addAll(loaded);
        }
    }

    public static void save() {
        store.save(FILE_NAME, students.getAll());
    }

    public static Student findStudent(String id) {
        for (Student s : students.getAll()) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        load();
        new SwingGUI();
    }
}
