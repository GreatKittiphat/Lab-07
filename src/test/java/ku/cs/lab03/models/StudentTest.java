package ku.cs.lab03.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;

    @BeforeEach
    void setupBeforeEach() {
        student = new Student("123", "ABC");
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 30 คะแนน")
    void testAddScore(){
        Student student = new Student("123", "ABC");
        student.addScore(30);
        assertEquals(30, student.getScore());

    }

    @Test
    @DisplayName("ทดสอบคำนวนเกรด F")
    void testCalculateGrade(){
        Student student = new Student("123", "ABC");
        assertEquals("F", student.grade());
    }

}