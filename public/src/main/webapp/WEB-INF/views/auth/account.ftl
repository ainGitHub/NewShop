<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="products">
    <#if user??>
    <ul>
        <li>
            <img src="<@spring.url value="/resources/image/user/"/>${user.avatar}" style="width: 160px;">

            <div class="product-info">
                <h3>Name ${user.username}</h3>

                <div class="product-desc">
                    <h4>${user.mail}</h4>

                    <p>Other information about<br> user</p>

                    <div>
                    <form action="<@spring.url value="/logout"/>" method="post" id="logoutForm">
                        <input type="submit" value="logout" class="btn-userGoods">
                    </form>
                    </div>
                </div>
            </div>
        </li>
    </ul>
    </#if>

    <h3>Заказы</h3>
    <#if orders?? && orders?has_content>
        <ul class="products">
            <#list orders as order>
                <li>
                    Создано ${order.createDate}<br>
                    Город ${order.address.city},
                    Улица ${order.address.street},
                    Дом   ${order.address.house},
                    Квартира ${order.address.flat},
                    Индекс ${order.address.index}<br>
                    <span>Сумма ${order.total_sum}</span><br>
                    Статус ${order.status}<br>
                ${order.good.name}
                </li>
            </#list>
        </ul>
    <#else>
        У вас нет заказов
    </#if>
</div>
</#macro>