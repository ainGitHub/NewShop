<#include "goodItem.ftl">
<#if goods?? && goods?has_content>
    <#list goods as good>
        <@goodItem good=good/>
    </#list>
</#if>