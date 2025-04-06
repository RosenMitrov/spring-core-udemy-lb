import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import student.services.StudentService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(
        basePackages = "student",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*File.*"
        )
)
public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        StudentService studentService = context.getBean("studentServiceImpl", StudentService.class);

        System.out.println(studentService.findYoungestStudent());
    }
}
