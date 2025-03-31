package student.services.impl;

import jakarta.inject.Inject;
import student.model.Student;
import student.repository.StudentRepository;
import student.services.StudentService;

import java.util.Comparator;

public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    @Inject
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findYoungestStudent() {
        return studentRepository.getAllStudents()
                .stream()
                .max(Comparator.comparing(Student::birthDay))
                .orElse(null);
    }
}
