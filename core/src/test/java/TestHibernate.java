import com.shop.itis.domain.*;
import com.shop.itis.service.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TestHibernate {

    private static UserService userService;
    private static RoleService roleService;
    private static GoodService goodService;
    private static CategoryService categoryService;
    private static CartService cartService;
    private static CartGoodService cartGoodService;
    private static OrderGoodService orderGoodService;
    private static OrderService orderService;
    private static AddressService addressService;

    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"data.xml"}, true);
        userService = context.getBean(UserService.class, "userService");

        roleService = context.getBean(RoleService.class, "roleService");

        categoryService = context.getBean(CategoryService.class, "categoryService");

        goodService = context.getBean(GoodService.class, "goodService");

        cartService = context.getBean(CartService.class, "cartService");

        orderService = context.getBean(OrderService.class);

        addressService = context.getBean(AddressService.class);

        cartGoodService = context.getBean(CartGoodService.class);
        orderGoodService = context.getBean(OrderGoodService.class);
    }

    public static void main(String[] args) throws SQLException {
        init();
        addGoods();
        //testGoodWrapper();
    }


    private static void testGoodWrapper() {
        Cart cart = new Cart();
        cartService.add(cart);

        List<Good> goods = goodService.getAllGoods();
        CartGood cartGood = new CartGood(goods.get(0), 1, cart);
        cartGoodService.add(cartGood);

        cart.getCartGood().add(cartGood);
        cartService.update(cart);

        Order order = new Order();
        order.setCreateDate(new Date());
        orderService.add(order);

        for (CartGood g : cart.getCartGood()) {
            OrderGood orderGood = new OrderGood(g.getGood(), g.getCount(), order);
            orderGoodService.add(orderGood);
            orderGoodService.add(orderGood);
            order.getOrderGoods().add(orderGood);
        }

        orderService.add(order);

        System.out.println(order.getOrderGoods().remove(new OrderGood(cartGood.getGood(), cartGood.getCount(), order)));
        orderService.add(order);
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


        for (int i = 0; i < 5; i++) {
            String d1 = "Детство - часть автобиографической трилогии М. Горького, рассказывающая о нелегкой жизни мальчика Алеши в семье деда.";
            Good g1 = new Good("Детство" + i, 100.0, 10, d1, c2);
            g1.setCountry("Russia");
            g1.setImage("/detctvo.jpg");

            String d2 = "В своих произведениях автор задаёт вечные вопросы бытия. Ответы на них поможет найти криминальный роман \"Преступление и наказание\".\n" +
                    "Каково это - убить?";
            Good g2 = new Good("Преступление и Наказание" + i, 200.0, 10, d2, c2);
            g2.setCountry("Russia");
            g2.setImage("/prestinakaz.jpg");

            String d3 = "Марлоу Хепворт найден мертвым в своем кабинете. Раскрыть это дело под силу только великому детективу – мадам Вастре.\n";
            Good g3 = new Good("Доктор Кто. Силуэт" + i, 150.0, 10, d3, c1);
            g3.setCountry("Russia");
            g3.setImage("/doctorkto.jpg");

            goodService.add(g1);
            goodService.add(g2);
            goodService.add(g3);
        }
    }
}
