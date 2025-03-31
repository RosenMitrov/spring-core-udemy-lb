import com.google.inject.Guice;
import com.google.inject.Injector;
import student.model.Student;
import student.repository.impl.FileStudentRepository;
import student.services.StudentService;
import student.services.impl.StudentServiceImpl;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new StudentModule());

        StudentService studentService = injector.getInstance(StudentService.class);

        System.out.println(studentService.findYoungestStudent());


    }
}
