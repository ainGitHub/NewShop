<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<div id="header">
    <h1 id="logo"><a href="<@spring.url value="/"/>">bookstore</a></h1>

    <!-- Cart -->
    <div id="cart">
        <a href="<@spring.url value="/cart"/>" class="cart-link">${menuCartWords}</a>

        <div class="c1"></div>
        <span>${menuGoods}: <strong id="cartCount"><#if cartGoodsCount??>${cartGoodsCount}<#else>0</#if></strong></span>
        <span>${menuSum}: <strong id="cartSum"><#if cartSum??>${cartSum}<#else>0</#if>руб</strong></span>
    </div>
    <!-- End Cart -->

    <!-- Navigation -->
    <div id="navigation">
        <ul>
            <li><a href="<@spring.url value="/"/>">${menuHome}</a></li>
            <li><a href="<@spring.url value="/contacts"/>">${menuSupport}</a></li>
            <li><a href="<@spring.url value="/account"/>">${menuAccount}</a></li>
            <li><a href="<@spring.url value="/cart"/>">${menuCart}</a></li>
            <li><a href="<@spring.url value="/contacts"/>">${menuContacts}</a></li>
            <li>
                <a href="<@spring.url value="/locale/change?locale=en_EN"/>">EN</a>
                <a href="<@spring.url value="/locale/change?locale=ru_RU"/>">RU</a>
            </li>
        </ul>
    </div>
    <!-- End Navigation -->
</div>