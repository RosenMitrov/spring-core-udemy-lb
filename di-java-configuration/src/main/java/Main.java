import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import student.config.AppConfig;
import student.services.StudentService;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService studentService = context.getBean("studentService", StudentService.class);

        System.out.println(studentService.findYoungestStudent());
    }
}
