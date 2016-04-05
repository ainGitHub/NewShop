<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
    <#if cartError??>
    <span class="cart-goods-empty">${cartError}</span>
    </#if>

    <#if cartGoods?? && cartGoods?has_content>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list cartGoods as wrapper>
            <@goodItem good=wrapper.good showBtnCart=false showDelete=true showDeleteFromOrder=false counter=wrapper.count/>
            </#list>
        </ul>
        <a href="<@spring.url value="/order"/>">
            <button class="btn order-btn">Заказать</button>
        </a>
    </div>
    </#if>
</#macro>