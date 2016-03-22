<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<div id="header">
    <h1 id="logo"><a href="<@spring.url value="/"/>">bookstore</a></h1>

    <!-- Cart -->
    <div id="cart">
        <a href="<@spring.url value="/cart"/>" class="cart-link">Ваши товары в Корзине</a>

        <div class="c1"></div>
        <span>Товаров: <strong><#if cartGoodsCount??>${cartGoodsCount}<#else>0</#if></strong></span>
        <span>Сумма: <strong><#if cartSum??>${cartSum}<#else>0</#if>руб</strong></span>
    </div>
    <!-- End Cart -->

    <!-- Navigation -->
    <div id="navigation">
        <ul>
            <li><a href="<@spring.url value="/"/>">Домой</a></li>
            <li><a href="#">Поддержка</a></li>
            <li><a href="<@spring.url value="/account"/>">Мой Аккаунт</a></li>
            <li><a href="#">Магазин</a></li>
            <li><a href="#">Контакты</a></li>
        </ul>
    </div>
    <!-- End Navigation -->
</div>