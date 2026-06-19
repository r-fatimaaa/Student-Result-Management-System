package backend;

import java.io.Serializable;

public class CourseInstructor implements Serializable {

    private String name;
    private String qualification;

    public CourseInstructor(String name, String qualification) {
        this.name = name;
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }
}
