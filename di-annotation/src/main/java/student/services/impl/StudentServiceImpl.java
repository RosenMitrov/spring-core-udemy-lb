package student.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import student.model.Student;
import student.repository.StudentRepository;
import student.services.StudentService;

import java.util.Comparator;

public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(@Qualifier("inMemoryRepository") StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findYoungestStudent() {
        return studentRepository.getAllStudents()
                .stream()
                .max(Comparator.comparing(Student::birthDay))
                .orElse(null);
    }

    @Override
    public void init() {
        System.out.println("The service manages " + +studentRepository.count() + " students.");
    }
}
