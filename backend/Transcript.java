package backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Transcript implements Serializable {

    private ArrayList<ResultEntry> results;

    public Transcript() {
        results = new ArrayList<>();
    }

    public void addResult(ResultEntry r) {
        results.add(r);
    }

    public ArrayList<ResultEntry> getResults() {
        return results;
    }

    public boolean hasCourse(String courseCode) {
        for (ResultEntry r : results) {
            if (r.getCourse().getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    // Total GPA sum (used by Student)
    public double getTotalGPA() {
        double total = 0.0;
        for (ResultEntry r : results) {
            total += r.getCourseGPA();
        }
        return total;
    }
}
