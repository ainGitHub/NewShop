<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="products">
    <#if user??>
    <ul>
        <li>
            <img src="/resources/image/user/${user.avatar}" style="width: 160px;">

            <div class="product-info">
                <h3>Name ${user.username}</h3>

                <div class="product-desc">
                    <h4>${user.mail}</h4>

                    <p>Other information about<br> user</p>

                    <form action="/logout" method="post" id="logoutForm">
                        <input type="submit" value="logout" class="btn-cart">
                    </form>
                </div>
            </div>
        </li>
    </ul>
    </#if>
</div>
</#macro>