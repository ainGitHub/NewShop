<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="products">
    <#if user??>
    <ul>
        <li>
            <img src="<@spring.url value="/resources/image/user/"/>${user.avatar}" style="width: 160px;">

            <div class="product-info">
                <h3>Name ${user.username}</h3>

                <div class="product-desc">
                    <h4>${user.mail}</h4>

                    <p>Other information about<br> user</p>

                    <div>
                    <form action="<@spring.url value="/logout"/>" method="post" id="logoutForm">
                        <input type="submit" value="logout" class="btn-cart">
                    </form>
                    </div>
                </div>
            </div>
        </li>
    </ul>
    </#if>
</div>
</#macro>