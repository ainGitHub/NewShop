<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<div style="margin-left: 40%;">
    <form action="<@spring.url value="/order/add"/>" method="post">
        <#if user??>
            <h3>Name ${user.username}</h3>
        </#if> <br>

        <h3>Address <input type="text" name="address" title="Address"></h3><br>
        <input type="submit" value="Купить" class="btn delete-btn">
    </form>
</div>
    <#if cart?? && cart.goods??>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list cart.goods as good>
            <@goodItem good=good showBtnCart=false showDelete=false showDeleteFromOrder=true/>
        </#list>
        </ul>
    </div>
    </#if>
</#macro>