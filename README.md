# SRMS Overview
- The Student Result Management Systme is designed to automate the process of managing
student’s academic records and calculation processes. SRMS is developed to make this process
easy and reliable. It allows teachers and faculty staff to enter student marks, calculate GPA and
view Student transcripts. This system also stores data using Serialization process so the records
are not lost when program frame is closed.
----------------------------------------------------------------------------------------------------------
# Class Descriptions
- Student Class
  Student cass is a blueprint for a student I the system. It is declared as an absract classs because it is not used directly. Instead different types of users extend it to instantiate it.
- ScienceStudent , ArtsStudent, EngineerStudent
  These classses extends the Student class and represent students from different programs. They mainly exist to show inheritance.
- Course Class
  This Class stores all the information about a course and has a conmposition realtion with CourseInstructor Class.
- CourseInstructor Class
  This class stores the information about the instructor of a Course.
- ResultEntry Class
  This class stores the marks of a student for a single course.
- Transcript Class
  It retrives the data for Specific student from ResultEntry class and keep all the result of a student.
- RecordList Generic Class
  This is a Generic class to store different types of Ojbects like Students from diffenet programms.
- DataStore Generic Class
  This class is used to save and load data from binary .dat files.
- ResultCalculator Interface
  This interface defines the formaulas and rules to calculate gardes and GPA.
  - SwingGUI class
  SwingGUI class handles the GUI of the system. It allows adding students, entering course marks, searching studets by IDs and viewing complete transcripts. It connects the frontend with the backend logic using a SRMSRunner class.
  -----------------------------------------------------------------------------------------------------
  # Implementation
Backened logic
   - Key Features
    • Use of OOP concepts
    • Automated GPA and grade calculation
    • Transcript Display for each Student using their StudentId
    • Scholarship eligibility criteria and showing a list of students who are eligible
    • Data saved using utility files
    • Simple and easy to use GUI using Swing Framework
GUI Design
   - GUI is created using Swing components.
    • Tables are used to display student data
    • Buttons are used for differnet actions
    • Panels rae used to make the interface a bit visualy good.
-----------------------------------------------------------------------------------------------------------------
# Future Enhancements
- Dashboard
- Database
- Login User authentication system
