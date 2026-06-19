package backend;

import java.io.Serializable;

public class ResultEntry implements Serializable {

    private Course course;
    private double marks;

    public ResultEntry(Course course, double marks) {
        this.course = course;
        this.marks = marks;
    }

    public Course getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    // Course GPA based on marks
    public double getCourseGPA() {
        return marks / 25.0; // simple & safe
    }

    public String getCourseGrade() {
        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 55) return "C";
        return "F";
    }
}
