package ku.cs.lab03.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import ku.cs.lab03.models.Student;
import ku.cs.lab03.models.StudentList;
import ku.cs.lab03.services.Datasource;
import ku.cs.lab03.services.FXRouter;
import ku.cs.lab03.services.ParseObject;
import ku.cs.lab03.services.StudentListFileDatasource;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public class StudentScoreController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField scoreTextField;

    private Datasource<StudentList> datasource;
    private StudentList studentList;
    private Student student;

    @FXML
    public void initialize() {
        datasource = new StudentListFileDatasource("data", "student-list.csv");
        studentList = datasource.readData();

        // รับข้อมูล studentId จากหน้าอื่น ผ่าน method FXRouter.getData()
        // โดยจำเป็นต้อง casting data type ให้ตรงกับหน้าที่ส่งข้อมูล
        ParseObject parseObject = (ParseObject) FXRouter.getData();
        String studentId = (String) parseObject.getItem("studentId");
        double test = (double) parseObject.getItem("test");
        // sout
        System.out.println(test);

        student = studentList.findStudentById(studentId);

        showStudent(student);

        errorLabel.setText("");

    }

    private void showStudent(Student student) {
        nameLabel.setText(student.getName());
        idLabel.setText(student.getId());
        // "" --> เพื่่อให้ double กลับมาเป็น String
        scoreLabel.setText("" + student.getScore());
    }

    @FXML
    public void handleGiveScoreButton() {
        String scoreString = scoreTextField.getText().trim();
        if (scoreString.equals("")) {
            errorLabel.setText("score is required");
            return;
        }
        try {
            double score = Double.parseDouble(scoreString);
            if (score < 0) {
                errorLabel.setText("score must be positive number");
                return;
            }
            errorLabel.setText("");
            studentList.giveScoreToId(student.getId(), score);
            scoreTextField.clear();
            datasource.writeData(studentList);
            showStudent(student);
        } catch (NumberFormatException e) {
            errorLabel.setText("score must be number");
        }
    }

    public void handleBackToStudentsTableButton() {
        try {
            FXRouter.goTo("students-table");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
