<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<style>


</style>
<div class="login-form">
<form name='loginForm'
      action="<@spring.url value="/login"/>" method='POST'>
    <p>
        <label>User:</label>
        <input type='text' name='username' value='' title="Логин">
    </p>

    <p>
        <label>Password:</label>
        <input type='password' name='password' title="Пороль"/>
    </p>

    <p>
        <label>remember me</label>
        <input type="checkbox" name="remember" title="RememberMe">
    </p>

    <p>
        <input name="submit" type="submit" value="Войти" class="btn-cart"/>
    </p><br>
</form>
    <a href="<@spring.url value="/registr"/>">Зарегистрироваться</a>
</div>
</#macro>
