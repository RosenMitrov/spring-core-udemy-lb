package student.services.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import student.model.Student;
import student.repository.FileBased;
import student.repository.MemoryBased;
import student.repository.StudentRepository;
import student.services.StudentService;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final Provider<StudentRepository> studentRepository;
    private final String message;

//    public StudentServiceImpl(@Named("inMemoryRepository") StudentRepository studentRepository,
//                              @Value("${init.message}") String message) {
//        this.studentRepository = studentRepository;
//        this.message = message;
//    }

//    public StudentServiceImpl(@FileBased StudentRepository studentRepository,
//                              @Value("${init.message}") String message) {
//        this.studentRepository = studentRepository;
//        this.message = message;
//    }

    public StudentServiceImpl(@Named("inMemoryRepository") Provider<StudentRepository> studentRepository,
                              @Value("${init.message}") String message) {
        this.studentRepository = studentRepository;
        this.message = message;
    }
//
//    @Override
//    public Student findYoungestStudent() {
//        return studentRepository.getAllStudents()
//                .stream()
//                .max(Comparator.comparing(Student::birthDay))
//                .orElse(null);
//    }


    @Override
    public Student findYoungestStudent() {
        return studentRepository.get()
                .getAllStudents()
                .stream()
                .max(Comparator.comparing(Student::birthDay))
                .orElse(null);
    }
//    @Override
//    @PostConstruct
//    public void init() {
//        System.out.printf(message, studentRepository.count());
//    }

    @Override
    @PostConstruct
    public void init() {
        System.out.printf(message, studentRepository.get().count());
    }

    @Override
    @PreDestroy
    public void destroy() {
        System.out.println("Goodbye! from student service, shutting down!");
    }
}
