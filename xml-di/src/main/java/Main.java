import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import student.services.StudentService;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        StudentService studentService = context.getBean("studentService", StudentService.class);

        System.out.println(studentService.findYoungestStudent());
    }
}
