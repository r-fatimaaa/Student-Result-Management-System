package backend;

import java.io.Serializable;

public class Course implements Serializable {

    private String courseCode;
    private String title;
    private int creditHours;
    private CourseInstructor instructor;

    public static int totalCourses = 0;

    public Course(String courseCode, String title, int creditHours,
                  CourseInstructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.instructor = instructor;
        totalCourses++;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public CourseInstructor getInstructor() {
        return instructor;
    }
}
