package ku.cs.lab03.models;

import ku.cs.lab03.services.Datasource;
import ku.cs.lab03.services.StudentHardCodeDatasource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    private StudentList studentList;

    @BeforeEach
    void setupBeforeEach() {
        Datasource<StudentList> datasource = new StudentHardCodeDatasource();
        studentList = datasource.readData();
    }

    @Test
    void testAddOneStudent() {
        int oldsize = studentList.getStudents().size();
        studentList.addNewStudent("123", "ABC");
        ArrayList<Student> stdArr = studentList.getStudents();
    }

}