package student.repository.impl;

import student.model.Student;
import student.repository.StudentRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class FileStudentRepository implements StudentRepository {

    @Override
    public List<Student> getAllStudents() {
        return new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("students.csv"))))
                .lines()
                .map(this::asStudent)
                .toList();
    }

    private Student asStudent(String line) {
        String[] lineData = line.split(",");
        return new Student(lineData[0].trim(), LocalDate.parse(lineData[1].trim()));
    }
}
