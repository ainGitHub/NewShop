<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
<style>


</style>
<div class="login-form">
<form name='loginForm'
      action="<@spring.url value="/login"/>" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value='' title="Логин"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' title="Пороль"/></td>
        </tr>
        <tr>
            <td>remember me</td>
            <td><input type="checkbox" name="remember" title="RememberMe"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Войти" class="btn-cart"/></td>
        </tr>
    </table>
</form>
    <a href="<@spring.url value="/registr"/>">Зарегистрироваться</a>
</div>
</#macro>
