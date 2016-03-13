<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="login-form">
<form name='loginForm'
      action="/registr" method='POST'>

    <table>
        <tr>
            <td>Username:</td>
            <td><input type='text' name='username' value='' title="�����"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' title="������"/></td>
        </tr>
        <tr>
            <td>email</td>
            <td><input type="email" name="email" title="email"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Зарегистрироваться" class="btn-cart"/></td>
        </tr>
    </table>
<#if _csrf??>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</#if>
</form>
</div>
</#macro>