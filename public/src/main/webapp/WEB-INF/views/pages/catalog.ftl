<#include "../mainTemplates/main.ftl">
<@mainTemplate/>
<#macro body>
    <#if message??>
    ${message}
    </#if>

<div class="products">
    <#if goods?? && goods?has_content>
        <ul id="goodList">
            <#include "goodItem.ftl">
             <#list goods as good>
            <@goodItem good=good showBtnCart=true showDelete=false showDeleteFromOrder=false counter=0/>
        </#list>
        </ul>
        <ul>
            <#if limit?? && goodsCount??>
                <#if limit < goodsCount >
                    <li class="last">
                        <div id="showMore" class="show-more-button" data-page="${page+1}" data-limit="${limit}">
                            <h3> Показать еще (<span id="limit">${limit}</span>) из <span
                                    id="goodsCount">${goodsCount-limit}</span></h3>
                        </div>
                    </li>
                </#if>
            </#if>
        </ul>
    </#if>
</div>
</#macro>