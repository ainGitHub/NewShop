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
            <li class="last">
                <div>
                    <a class="btn-cart" style="margin:auto">
                        Заказать
                    </a>
                </div>
            </li>
        </ul>
    </div>
    </#if>
</#macro>