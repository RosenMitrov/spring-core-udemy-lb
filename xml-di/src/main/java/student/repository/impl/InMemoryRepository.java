package student.repository.impl;

import student.model.Student;
import student.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

public class InMemoryRepository implements StudentRepository {

    private final List<Student> students = List.of(
            new Student("Memory Memorov", LocalDate.of(1989, 12, 16)),
            new Student("Test Testov", LocalDate.of(1995, 3, 8))
    );

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public long count() {
        return getAllStudents().size();
    }
}
