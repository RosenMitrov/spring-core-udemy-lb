package student.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import student.repository.StudentRepository;
import student.repository.impl.FileStudentRepository;
import student.repository.impl.InMemoryRepository;
import student.services.StudentService;
import student.services.impl.StudentServiceImpl;

import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public StudentRepository fileStudentRepository() {
        return new FileStudentRepository();
    }

    @Bean
    public StudentRepository inmemoryStudentRepository() {
        return new InMemoryRepository();
    }

    @Bean
    public StudentService studentService(List<StudentRepository> studentRepositories,
                                         @Value("${init.message}") String message) {
        return new StudentServiceImpl(studentRepositories, message);
    }
}
