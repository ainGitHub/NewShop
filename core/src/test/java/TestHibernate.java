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
    private static CartIdService cartIdService;
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

        cartIdService = context.getBean(CartIdService.class);
    }

    public static void main(String[] args) throws SQLException {
        init();
        //cartService.deleteAll(userService.getUserByUsername("ainur"));
        //testUser();
        //
        //
        //addGoods();
        //newTest();
        createOrders();
        //testCart();
        //testFilters();
        //testCategory();
        //testOrders();

    }


    private static void newTest() {
        UserInfo userInfo = new UserInfo("ainur", "1234", "ainur@mail.ru");
        userService.add(userInfo);

        List<Good> goods = goodService.getAllGoods();

        Cart cart = cartService.getById((long) 69);
        if (cart == null)
            cart = new Cart();
        cart.setUserInfo(userInfo);
        cartService.add(cart);


        GoodsWrapper goodsWrapper = null;
        int i = 0;
        for (Good g : goods) {
            i++;
            goodsWrapper = new GoodsWrapper();
            goodsWrapper.setGood(g);
            goodsWrapper.setCount(i);
            goodsWrapper.setCart(cart);
            cartIdService.add(goodsWrapper);

            cart.getGoodsWrapper().add(goodsWrapper);
            cartService.add(cart);
        }


        userInfo.setCart(cart);
        userService.add(userInfo);


        cartIdService.delete(cart, goods.get(0));
    }

    private static void createOrders() {
        List<Good> goods = goodService.getAllGoods();

        UserInfo userInfo = new UserInfo("ainur", "1234", "ainur@mail.ru");
        userService.add(userInfo);

        Address address = new Address("Moscov", "Pushkin", 12, 21, 123456);
        address.setUserInfo(userInfo);
        addressService.update(address);

        Address address2 = new Address("Kazan", "Lenina", 21, 12, 654321);
        address.setUserInfo(userInfo);
        addressService.update(address2);

        Cart cart = cartService.getById((long) 69);
        int i = 0;
        for (GoodsWrapper g : cart.getGoodsWrapper()) {
            i++;
            Order o = new Order(address, new Date(), 100.0, "paypal", "to check", i);
            o.getGoodsWrapper().add(g);
            orderService.add(o);

            userInfo.getOrders().add(o);
        }

        Order o2 = new Order(address2, new Date(), 100.0, "paypal", "to check", 10);
        orderService.add(o2);

        userInfo.getOrders().add(o2);
        userService.add(userInfo);


    }

    private static void testOrders() {
        UserInfo userInfo = userService.getUserByUsername("ainur");
        List<Order> orders = orderService.getAllOrders(userInfo);
        for (Order o : orders) {
            System.out.println(o.getCreateDate());
            System.out.println(o.getAddress().getCity());
        }
    }

    private static void testUser() {
        UserInfo u = new UserInfo();
        u.setUsername("ainur");
        u.setPassword("e10adc3949ba59abbe56e057f20f883e");//123456
        u.setMail("ainur@mail.ru");
        u.setEnabled(true);

        userService.add(u);

        UserRoles r = new UserRoles();
        r.setRole("ROLE_USER");
        r.setUserInfo(u);
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

    private static void testCart() {
       /* //addGoods();
        List<Good> goods = goodService.getAllGoods();*/

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("admin");
        userService.add(userInfo);


        // UserGoods userGoods = new UserGoods(userInfo, goods.get(0), 10);
        // cartService.update(userGoods);

        // List<UserGoods> userGoodses = cartService.getUserAllGoods("admin");
        //for (UserGoods c : userGoodses) {
        //     System.out.println(c.getGood().getName());
        // }

       /* System.out.println(cartService.getById(goods.get(0).getId(), userInfo.getUsername()).getGood().getName());
        //cartService.delete(goods.get(0).getId(), userInfo.getUsername());
        cartService.update(goods.get(0), userInfo, 5);*/
    }

    private static void testFilters() {
        List<Good> goods = goodService.getGoodsByCategoryPriceName((long) 2, 0.0, 151.0, "т");
        for (Good g : goods) {
            System.out.println(g.getName());
        }
    }

    private static void testCategory() {
        List<Category> categories = categoryService.getAllCategories();
        for (Category c : categories) {
            System.out.println(c.getName());
        }
    }

}
