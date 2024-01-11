package ku.cs.lab03.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.cs.lab03.models.Student;
import ku.cs.lab03.models.StudentList;
import ku.cs.lab03.services.Datasource;
import ku.cs.lab03.services.FXRouter;
import ku.cs.lab03.services.ParseObject;
import ku.cs.lab03.services.StudentListFileDatasource;

import java.io.IOException;

public class StudentsTableController {
    @FXML
    private TableView<Student> studentsTableView;
    private StudentList studentList;
    private Datasource<StudentList> datasource;

    @FXML
    public void initialize() {
        datasource = new StudentListFileDatasource("data", "student-List.csv");
        studentList = datasource.readData();
        showTable(studentList);

        studentsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue observable, Student oldValue, Student newValue) {
                if (newValue != null) {
                    try {
                        // FXRouter.goTo สามารถส่งข้อมูลไปยังหน้าที่ต้องการได้ โดยกำหนดเป็น parameter ที่สอง
                        ParseObject parseObject = new ParseObject();
                        parseObject.addItem("studentId", newValue.getId());
                        parseObject.addItem("test", 12.34);
                        FXRouter.goTo("student-score", parseObject);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void showTable(StudentList studentList) {
        // กำหนด column ให้มี title ว่า ID และใช้ค่าจาก attribute id ของ object Student
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // กำหนด column ให้มี title ว่า Name และใช้ค่าจาก attribute name ของ object Student
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // กำหนด column ให้มี title ว่า Score และใช้ค่าจาก attribute score ของ object Student
        TableColumn<Student, Double> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // ล้าง column เดิมทั้งหมดที่มีอยู่ใน table แล้วเพิ่ม column ใหม่
        studentsTableView.getColumns().clear();
        studentsTableView.getColumns().add(idColumn);
        studentsTableView.getColumns().add(nameColumn);
        studentsTableView.getColumns().add(scoreColumn);

        studentsTableView.getItems().clear();

        // ใส่ข้อมูล Student ทั้งหมดจาก studentList ไปแสดงใน TableView
        for (Student student: studentList.getStudents()) {
            studentsTableView.getItems().add(student);
        }
    }


}
