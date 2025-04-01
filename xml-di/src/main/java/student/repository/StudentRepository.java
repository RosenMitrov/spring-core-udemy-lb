package student.repository;

import student.model.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> getAllStudents();
    long count();
}
