<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<div style="margin-left: 40%;">
    <#if user??>
        <h3>Здравствуйте ${user.username} Заполните пожалуйста поля</h3>
    </#if> <br>
    <form action="<@spring.url value="/order/add"/>" method="post">
        <h3>Город <input type="text" name="city" title="Город"></h3><br>

        <h3>Улица <input type="text" name="street" title="Улица"></h3><br>

        <h3>Дом <input type="number" name="house" title="Дом"></h3><br>

        <h3>Квартира <input type="text" name="flat" title="Квартира"></h3><br>

        <h3>Индекс <input type="number" name="index" title="Индекс"></h3><br>
        <input type="submit" value="Купить" class="btn delete-btn">
    </form>
</div>
    <#if cartGoods?? && cartGoods?has_content>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list cartGoods as good>
            <@goodItem good=good showBtnCart=false showDelete=false showDeleteFromOrder=true/>
        </#list>
        </ul>
    </div>
    </#if>
</#macro>