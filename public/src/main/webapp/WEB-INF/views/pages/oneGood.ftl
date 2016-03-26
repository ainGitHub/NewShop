<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>

    <#if oneGood??>
    <div class="products">
        <ul id="goodList">
            <#include "goodItem.ftl">
        <@goodItem good=oneGood showBtnCart=true showDelete=false/>
        </ul>
    </div>
    <#else >
    Not find
    </#if>
</#macro>