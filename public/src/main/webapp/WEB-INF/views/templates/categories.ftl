<div class="box categories">
    <h2>Categories <span></span></h2>

    <div class="box-content">
        <ul>
        <#if menuList??>
            <#list menuList as category>
                <li><a href="<@spring.url value="/category?category=${category.id}"/>">${category.name}</a></li>
            </#list>
        </#if>
        </ul>
    </div>
</div>