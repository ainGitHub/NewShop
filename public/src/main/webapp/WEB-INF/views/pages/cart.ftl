<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
    <#if cartError??>
    <span class="cart-goods-empty">${cartError}</span>
    </#if>

    <#if cartGoods??>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list cartGoods as good>
                <@goodItem good=good showBtnCart=false showDelete=true/>
            </#list>
        </ul>
        <button class="btn order-btn">Заказать</button>
    </div>
    </#if>
</#macro>