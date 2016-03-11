import com.shop.itis.domain.User;
import com.shop.itis.domain.UserRoles;
import com.shop.itis.service.RoleService;
import com.shop.itis.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestHibernate {

    private static UserService userService;
    private static RoleService roleService;

    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"data.xml"}, true);
        userService = context.getBean(UserService.class, "userService");

        roleService = context.getBean(RoleService.class, "roleService");
    }

    public static void main(String[] args) throws SQLException {
        init();
        testUser();
    }

    private static void testUser() {
        User u = new User();
        u.setUsername("ainur");
        u.setPassword("e10adc3949ba59abbe56e057f20f883e");//123456
        u.setMail("ainur@mail.ru");
        u.setEnabled(true);

        userService.add(u);

        UserRoles r = new UserRoles();
        r.setRole("ROLE_USER");
        r.setUser(u);
        roleService.add(r);
    }

}
