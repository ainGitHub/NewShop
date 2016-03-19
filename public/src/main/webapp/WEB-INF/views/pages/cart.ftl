<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
    <#if Session.cart??>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list Session.cart.goods as good>
                <@goodItem good=good showBtnCart=false/>
            </#list>
            <li class="last">
                <div>
                    <button class="btn-cart" onclick="return false;">
                        Заказать
                    </button>
                </div>
            </li>
        </ul>
    </div>
    </#if>
</#macro>