import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import student.services.StudentService;

@ComponentScan(
        basePackages = "student"
)
public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        StudentService studentService = context.getBean("studentService", StudentService.class);

        System.out.println(studentService.findYoungestStudent());
    }
}
