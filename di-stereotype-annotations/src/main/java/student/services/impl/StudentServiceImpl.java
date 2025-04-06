package student.services.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import student.model.Student;
import student.repository.StudentRepository;
import student.services.StudentService;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;
    private final String message;

    public StudentServiceImpl(List<StudentRepository> studentRepositories,
                              @Value("${init.message}") String message) {
        this.studentRepository = new CompositeStudentRepository(studentRepositories);
        this.message = message;
    }

    @Override
    public Student findYoungestStudent() {
        return studentRepository.getAllStudents()
                .stream()
                .max(Comparator.comparing(Student::birthDay))
                .orElse(null);
    }

    static class CompositeStudentRepository implements StudentRepository {

        private final List<StudentRepository> studentRepositories;

        public CompositeStudentRepository(List<StudentRepository> studentRepositories) {
            this.studentRepositories = studentRepositories;
        }

        @Override
        public List<Student> getAllStudents() {
            return studentRepositories
                    .stream()
                    .flatMap(sr -> sr.getAllStudents().stream())
                    .toList();
        }

        @Override
        public long count() {
            return getAllStudents().size();
        }
    }

    @Override
    @PostConstruct
    public void init() {
        System.out.printf(message, studentRepository.count());
    }
}
