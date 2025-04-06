package student.services;

import student.model.Student;

public interface StudentService {
    Student findYoungestStudent();

    void init();

    void destroy();
}
