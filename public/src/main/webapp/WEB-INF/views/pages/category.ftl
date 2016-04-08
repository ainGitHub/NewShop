<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
    <#if categoryGoods??>
        <#include "goodItem.ftl">
    <div class="products">
        <ul>
            <#list categoryGoods as good>
            <@goodItem good=good showDeleteFromOrder=false showDelete=false showBtnCart=true counter=0/>
    </#list>
        </ul>
    </div>
    <#else>
    Товары не найдены!
    </#if>
</#macro>