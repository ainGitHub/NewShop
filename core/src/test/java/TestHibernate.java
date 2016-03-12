import com.shop.itis.domain.Category;
import com.shop.itis.domain.Good;
import com.shop.itis.domain.User;
import com.shop.itis.domain.UserRoles;
import com.shop.itis.service.CategoryService;
import com.shop.itis.service.GoodService;
import com.shop.itis.service.RoleService;
import com.shop.itis.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestHibernate {

    private static UserService userService;
    private static RoleService roleService;
    private static GoodService goodService;
    private static CategoryService categoryService;

    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"data.xml"}, true);
        userService = context.getBean(UserService.class, "userService");

        roleService = context.getBean(RoleService.class, "roleService");

        categoryService = context.getBean(CategoryService.class, "categoryService");

        goodService = context.getBean(GoodService.class, "goodService");
    }

    public static void main(String[] args) throws SQLException {
        init();
        //testUser();
        addGoods();
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


    private static void addGoods() {
        Category c1 = new Category("Фантастика", "fentezy");
        Category c2 = new Category("Классика", "classic");
        Category c3 = new Category("Детективы", "detective");
        Category c4 = new Category("Приключения", "adventure");
        Category c5 = new Category("Исторические", "history");
        Category c6 = new Category("Документальные", "document");

        categoryService.add(c1);
        categoryService.add(c2);
        categoryService.add(c3);
        categoryService.add(c4);
        categoryService.add(c5);
        categoryService.add(c6);


        String d1 = "Детство - часть автобиографической трилогии М. Горького, рассказывающая о нелегкой жизни мальчика Алеши в семье деда.";
        Good g1 = new Good("Детство", 100.0, 10, d1, c2);
        g1.setCountry("Russia");
        g1.setImage("/detctvo.jpg");

        String d2 = "В своих произведениях автор задаёт вечные вопросы бытия. Ответы на них поможет найти криминальный роман \"Преступление и наказание\".\n" +
                "Каково это - убить?";
        Good g2 = new Good("Преступление и Наказание", 200.0, 10, d2, c2);
        g2.setCountry("Russia");
        g2.setImage("/prestinakaz.jpg");

        String d3 = "Марлоу Хепворт найден мертвым в своем кабинете. Раскрыть это дело под силу только великому детективу – мадам Вастре.\n";
        Good g3 = new Good("Доктор Кто. Силуэт", 150.0, 10, d3, c1);
        g3.setCountry("Russia");
        g3.setImage("/doctorkto.jpg");

        goodService.add(g1);
        goodService.add(g2);
        goodService.add(g3);
    }

}
