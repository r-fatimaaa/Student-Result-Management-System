package gui;

import backend.*;
import utility.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SwingGUI extends JFrame {

    // Input fields
    JTextField idField, nameField, courseCodeField, courseTitleField,
            marksField, instructorNameField, instructorQualField, searchField;
    JComboBox<String> programBox;

    DefaultTableModel model;
    JTable table;

    public SwingGUI() {

        setTitle("COMSATS SRMS Portal");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(headerPanel(), BorderLayout.NORTH);
        add(leftPanel(), BorderLayout.WEST);
        add(centerPanel(), BorderLayout.CENTER);
        add(bottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    // ================= HEADER =================
    private JPanel headerPanel() {
        JPanel p = new JPanel();
        p.setBackground(new Color(0, 51, 102));
        p.setPreferredSize(new Dimension(1000, 60));

        JLabel title = new JLabel("COMSATS SRMS PORTAL");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        p.add(title);
        return p;
    }

    // ================= LEFT PANEL =================
    private JPanel leftPanel() {
        JPanel p = new JPanel(new GridLayout(0, 2, 8, 8));
        p.setBorder(BorderFactory.createTitledBorder("Add Student"));
        p.setPreferredSize(new Dimension(380, 0));

        idField = new JTextField();
        nameField = new JTextField();
        programBox = new JComboBox<>(new String[]{"Science", "Arts", "Engineering"});
        courseCodeField = new JTextField();
        courseTitleField = new JTextField();
        marksField = new JTextField();
        instructorNameField = new JTextField();
        instructorQualField = new JTextField();

        p.add(new JLabel("Student ID"));
        p.add(idField);
        p.add(new JLabel("Student Name"));
        p.add(nameField);
        p.add(new JLabel("Program"));
        p.add(programBox);
        p.add(new JLabel("Course Code"));
        p.add(courseCodeField);
        p.add(new JLabel("Course Title"));
        p.add(courseTitleField);
        p.add(new JLabel("Marks"));
        p.add(marksField);
        p.add(new JLabel("Instructor Name"));
        p.add(instructorNameField);
        p.add(new JLabel("Instructor Qualification"));
        p.add(instructorQualField);

        JButton addBtn = new JButton("Add Student / Course");
        addBtn.addActionListener(e -> addStudentCourse());
        p.add(addBtn);

        return p;
    }

    // ================= CENTER PANEL =================
    private JPanel centerPanel() {

        model = new DefaultTableModel(
                new String[]{"Student ID", "Program", "CGPA", "Grade", "Scholarship"}, 0);

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        JPanel p = new JPanel(new BorderLayout());
        p.add(sp, BorderLayout.CENTER);

        return p;
    }

    // ================= BOTTOM PANEL =================
    private JPanel bottomPanel() {

        JPanel p = new JPanel();

        searchField = new JTextField(10);

        JButton searchBtn = new JButton("Search ID");
        JButton showAllBtn = new JButton("Show All");
        JButton transcriptBtn = new JButton("View Transcript");
        JButton scholarshipBtn = new JButton("Scholarship Students");

        searchBtn.addActionListener(e -> searchStudent());
        showAllBtn.addActionListener(e -> refreshTable());
        transcriptBtn.addActionListener(e -> viewTranscript());
        scholarshipBtn.addActionListener(e -> showScholarshipStudents());

        p.add(new JLabel("Student ID:"));
        p.add(searchField);
        p.add(searchBtn);
        p.add(showAllBtn);
        p.add(transcriptBtn);
        p.add(scholarshipBtn);

        return p;
    }

    // ================= LOGIC METHODS =================

    private void addStudentCourse() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String program = programBox.getSelectedItem().toString();
            String cCode = courseCodeField.getText();
            String cTitle = courseTitleField.getText();
            double marks = Double.parseDouble(marksField.getText());
            String instName = instructorNameField.getText();
            String instQual = instructorQualField.getText();

            Student s = SRMSRunner.findStudent(id);

            if (s == null) {
                if (program.equals("Science"))
                    s = new ScienceStudent(id, name);
                else if (program.equals("Arts"))
                    s = new ArtsStudent(id, name);
                else
                    s = new EngineeringStudent(id, name);

                SRMSRunner.students.add(s);
            }

            CourseInstructor ci = new CourseInstructor(instName, instQual);
            Course c = new Course(cCode, cTitle, 3, ci);

            boolean added = s.addCourse(c, marks);
            if (!added) {
                JOptionPane.showMessageDialog(this, "Course already exists for this student!");
            }

            SRMSRunner.save();
            refreshTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Student s : SRMSRunner.students.getAll()) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getProgram(),
                    String.format("%.2f", s.calculateGPA()),
                    s.calculateGrade(),
                    s.hasScholarship() ? "Yes" : "No"
            });
        }
    }

    private void searchStudent() {
        model.setRowCount(0);
        Student s = SRMSRunner.findStudent(searchField.getText());
        if (s != null) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getProgram(),
                    String.format("%.2f", s.calculateGPA()),
                    s.calculateGrade(),
                    s.hasScholarship() ? "Yes" : "No"
            });
        }
    }

    private void showScholarshipStudents() {
        model.setRowCount(0);
        for (Student s : SRMSRunner.students.getAll()) {
            if (s.hasScholarship()) {
                model.addRow(new Object[]{
                        s.getId(),
                        s.getProgram(),
                        String.format("%.2f", s.calculateGPA()),
                        s.calculateGrade(),
                        "Yes"
                });
            }
        }
    }

    private void viewTranscript() {

    int row = table.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this,
                "Please select a student from the table first.");
        return;
    }

    String id = table.getValueAt(row, 0).toString();
    Student s = SRMSRunner.findStudent(id);

    if (s == null) {
        JOptionPane.showMessageDialog(this, "Student not found!");
        return;
    }

    JDialog d = new JDialog(this, id + " - Transcript", true);
    d.setSize(750, 450);
    d.setLocationRelativeTo(this);
    d.setLayout(new BorderLayout());

    // ---------- HEADER ----------
    JPanel header = new JPanel(new GridLayout(2, 1));
    header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    header.add(new JLabel("Name: " + s.getName() +
            " | Program: " + s.getProgram()));

    header.add(new JLabel("CGPA: " +
            String.format("%.2f", s.calculateGPA()) +
            " | Grade: " + s.calculateGrade() +
            " | Scholarship: " +
            (s.hasScholarship() ? "Eligible" : "Not Eligible")));

    d.add(header, BorderLayout.NORTH);

    // ---------- TABLE ----------
    DefaultTableModel tm = new DefaultTableModel(
            new String[]{"Course Code", "Marks", "Course GPA", "Grade", "Instructor"}, 0);

    for (ResultEntry r : s.getTranscript().getResults()) {
        tm.addRow(new Object[]{
                r.getCourse().getCourseCode(),
                r.getMarks(),
                String.format("%.2f", r.getCourseGPA()),
                r.getCourseGrade(),
                r.getCourse().getInstructor().getName()
        });
    }

    JTable t = new JTable(tm);
    d.add(new JScrollPane(t), BorderLayout.CENTER);

    // ---------- FOOTER ----------
    JButton close = new JButton("Close");
    close.addActionListener(e -> d.dispose());
    d.add(close, BorderLayout.SOUTH);

    d.setVisible(true);
}
}
