import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import student.services.StudentService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(
        basePackages = "student"
)
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        context.registerShutdownHook();

        StudentService studentService = context.getBean("studentServiceImpl", StudentService.class);

        System.out.println(studentService.findYoungestStudent());
    }
}
