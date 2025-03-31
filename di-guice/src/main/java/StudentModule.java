import com.google.inject.AbstractModule;
import student.repository.StudentRepository;
import student.repository.impl.FileStudentRepository;
import student.services.StudentService;
import student.services.impl.StudentServiceImpl;

public class StudentModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StudentRepository.class).to(FileStudentRepository.class);
        bind(StudentService.class).to(StudentServiceImpl.class);
    }
}
