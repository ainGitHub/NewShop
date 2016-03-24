import com.shop.itis.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMailing {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mail.xml");

        //Get the mailer instance
        MailService mailer = (MailService) context.getBean("mailService");

        //Send a composed mail
        mailer.sendMail("ainurmullin@mail.ru", "Test Subject", "Testing body", "ainur6969@gmail.com");
    }

}
