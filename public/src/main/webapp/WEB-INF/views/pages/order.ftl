<#include "../mainTemplates/main.ftl">
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@mainTemplate />
<#macro body>
<div>
    <#if userAddress??>
        <h2> Ваши адреса</h2> <br>

        <div style="width: 50%; display: inline-block;">
        <#list userAddress as address>
            <div class="address" data-city="${address.city}" data-street="${address.street}"
                 data-house="${address.house}" data-flat="${address.flat!}"
                 data-index="${address.index}">
                Город: ${address.city},
                Улица: ${address.street},
                Дом: ${address.house},
                Квартира: ${address.flat!},
                Идекс: ${address.index};
            </div>
        </#list>
        </div>
    </#if>
    <div class="order-form">
        <#if userInfo??>
            <span>Здравствуйте <strong>${userInfo.username}</strong> Заполните пожалуйста поля</span><br>
    </#if> <br>

    <@form.form commandName="address_form" acceptCharset="UTF-8">
        <h3>Город <@form.input path="city" title="Город" id="city"/></h3>
        <@form.errors path="city" cssClass="error"/><br><br>

        <h3>Улица <@form.input path="street" title="Улица" id="street"/></h3>
        <@form.errors path="street" cssClass="error"/><br><br>

        <h3>Дом <@form.input path="house" title="Дом" id="house"/></h3>
        <@form.errors path="house" cssClass="error"/><br><br>

        <h3>Квартира <@form.input path="flat" title="Квартира" id="flat"/></h3>
        <@form.errors path="flat" cssClass="error"/><br><br>

        <h3>Индекс <@form.input path="index" title="Индекс" id="index"/></h3>
        <@form.errors path="index" cssClass="error"/><br><br>

        <input type="submit" value="Купить" class="btn delete-btn">
    </@form.form>
</div>
</div>
    <#if cart?? && cart.cartGood?has_content>
    <div class="products">
        <ul>
            <#include "goodItem.ftl">
            <#list cart.cartGood as good>
            <@goodItem good=good.good showBtnCart=false showDelete=false showDeleteFromOrder=false counter=1/>
        </#list>
        </ul>
    </div>
    </#if>
</#macro>