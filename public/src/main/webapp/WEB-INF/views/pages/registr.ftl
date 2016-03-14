<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="login-form">
    <form name="loginForm" action="/registr" method='post' enctype="multipart/form-data">
        <h1> Регистрация </h1>

        <p>
            <label>Ваш логин</label>
            <input name="username" required="required" type="text" placeholder="myname1"/>
        </p>

        <p>
            <label> Ваш e-mail</label>
            <input name="email" required="required" type="email" placeholder="sitehere.ru@my.com"/>
        </p>

        <p>
            <label>Ваш пароль </label>
            <input name="password" required="required" type="password" placeholder="123456"/>
        </p>
    <#-- <p>
         <label>Пароль еще раз</label>
         <input name="password_confirm" required="required" type="password" placeholder="123456"/>
     </p>-->
        <p>
            <label>Photo</label>
            <input name="photo" type="file">
        </p>

        <p>
            <input type="submit" value="Регистрация" class="btn-cart"/>
        </p>

        <p>
            Уже зарегистрированы ?
            <a href="/login" class="to_register"> Войдите на сайт </a>
        </p>
    </form>
</div>
</#macro>