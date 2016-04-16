<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="products">
    <#if userInfo??>
    <ul>
        <li>
            <img src="<@spring.url value="/resources/image/user/"/>${userInfo.avatar}" style="width: 160px;">

            <div class="product-info">
                <h3>Name ${userInfo.username}</h3>

                <div class="product-desc">
                    <h4>${userInfo.mail}</h4>

                    <p>Other information about</p>

                    <div>
                    <form action="<@spring.url value="/logout"/>" method="post" id="logoutForm">
                        <input type="submit" value="logout" class="btn-cart">
                    </form>
                    </div>
                </div>
            </div>
        </li>
    </ul>
    </#if>

    <#if orders?? && orders?has_content>
        <h3 align="center">Ваши Заказы</h3>
        <ul class="products">
            <#list orders as order>
                <li>
                    <div class="order-info">
                        <span class="date-info">Создано ${order.createDate}</span><br>
                        Адрес:
                        <#if order.address.city??> Город ${order.address.city},</#if>
                        <#if order.address.street??> Улица ${order.address.street},</#if>
                        <#if order.address.house??>  Дом   ${order.address.house},</#if>
                    <#if order.address.flat??>Квартира ${order.address.flat},</#if>
                        <#if order.address.index??>Индекс ${order.address.index}</#if>
                        <br>

                        <span>Сумма ${order.total_sum}</span><br>
                        Статус: ${order.status}<br>

                    <#if order.orderGoods??>
                        Товары:
                        <#list order.orderGoods as goodWrapper>
                        ${goodWrapper.good.name};
                        </#list>
                    </#if>
                        <#if order.status = "Заказ на проверке">
                            <form action="<@spring.url value='/order/delete'/>" method="post">
                                <input name="id" hidden type="text" value="${order.id}">
                                <input class="btn delete-btn" type="submit" value="Удалить"
                                       style="position: absolute; right: 0; top: 0;">
                            </form>
                        </#if>
                    </div>
                </li>
            </#list>
        </ul>
    <#else>
        <h3 align="center"> У вас нет заказов</h3>
    </#if>
</div>
</#macro>