<#include "../mainTemplates/main.ftl">
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@mainTemplate />
<#macro body>
<div style="margin-left: 40%;">
    <#if user??>
        <h3>Здравствуйте ${user.username} Заполните пожалуйста поля</h3>
    </#if> <br>

    <@form.form commandName="address_form" acceptCharset="UTF-8">
        <h3>Город <@form.input path="city" title="Город"/></h3>
        <@form.errors path="city" cssClass="error"/><br><br>

        <h3>Улица <@form.input path="street" title="Улица"/></h3>
        <@form.errors path="street" cssClass="error"/><br><br>

        <h3>Дом <@form.input path="house" title="Дом"/></h3>
        <@form.errors path="house" cssClass="error"/><br><br>

        <h3>Квартира <@form.input path="flat" title="Квартира"/></h3>
        <@form.errors path="flat" cssClass="error"/><br><br>

        <h3>Индекс <@form.input path="index" title="Индекс"/></h3>
        <@form.errors path="index" cssClass="error"/><br><br>

        <input type="submit" value="Купить" class="btn delete-btn">
    </@form.form>
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