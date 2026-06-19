package backend;

import java.io.Serializable;

public abstract class Student implements Serializable {

    protected String id;
    protected String name;
    protected String program;
    protected Transcript transcript;
    protected int totalCourses;

    public static int totalStudents = 0;

    public Student(String id, String name, String program) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.transcript = new Transcript();
        this.totalCourses = 0;
        totalStudents++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    // Prevent duplicate course + increment totalCourses
    public boolean addCourse(Course c, double marks) {
        if (transcript.hasCourse(c.getCourseCode())) {
            return false;
        }
        transcript.addResult(new ResultEntry(c, marks));
        totalCourses++;
        return true;
    }

    // Overall CGPA depends on totalCourses
    public double calculateGPA() {
        if (totalCourses == 0) return 0.0;
        return transcript.getTotalGPA() / totalCourses;
    }

    public String calculateGrade() {
        double gpa = calculateGPA();

        if (gpa >= 3.5) return "A";
        if (gpa >= 3.0) return "B";
        if (gpa >= 2.0) return "C";
        return "F";
    }

    public boolean hasScholarship() {
        return calculateGrade().equals("A");
    }
}
