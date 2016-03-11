<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="products">
    <ul>
        <li>
            <img src="/resources/image/big1.jpg">

            <div class="product-info">
                <h3>Name ${username}</h3>

                <div class="product-desc">
                    <h4>Address</h4>

                    <p>Other information about<br> user</p>

                    <form action="/logout" method="post" id="logoutForm">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input type="submit" value="logout">
                    </form>
                </div>
            </div>
        </li>
    </ul>
</div>
</#macro>