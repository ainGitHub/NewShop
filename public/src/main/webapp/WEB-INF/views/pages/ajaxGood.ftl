<#include "goodItem.ftl">
<#if goods?? && goods?has_content>
    <#list goods as good>
        <@goodItem good=good showBtnCart=true showDelete=false showDeleteFromOrder=false counter=1/>
    </#list>
</#if>