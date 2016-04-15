<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
    <#if cartError??>
    <div align="center">
        <span class="cart-goods-empty error">${cartError}</span>
    </div>
    </#if>

    <#if cart?? && cart.cartGood?has_content>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list cart.cartGood as wrapper>
            <@goodItem good=wrapper.good showBtnCart=false showDelete=true showDeleteFromOrder=false counter=wrapper.count/>
            </#list>
        </ul>
        <a href="<@spring.url value="/order"/>">
            <button class="btn order-btn">Заказать</button>
        </a>
    </div>
    </#if>
</#macro>