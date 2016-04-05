<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "../mainTemplates/main.ftl">
<@mainTemplate />
<#macro body>
<div class="login-form">
    <@form.form commandName="regform" action='registr' method='post' enctype="multipart/form-data" acceptCharset="UTF-8">
        <h1> Регистрация </h1>
        <br>
        <span class="error">
            *<@form.errors path="username"/>
        </span>
        <p>
            <label>Ваш логин</label>
            <@form.input path="username"/>
        </p><br>

         <span class="error">
             *<@form.errors path="email"/>
         </span>
        <p>
            <label> Ваш e-mail</label>
            <@form.input path="email"/>
        </p><br>


        <span class="error">
            *<@form.errors path="password"/>
        </span>
        <p>
            <label>Ваш пароль </label>
            <@form.password path="password" />
        </p><br>

    <#-- <p>
         <label>Пароль еще раз</label>
         <input name="password_confirm" required="required" type="password" placeholder="123456"/>
     </p>-->
        <p>
            <label>Photo</label>
            <input name="photo" type="file">
        </p>

        <p>
            <input type="submit" value="Регистрация" class="btn-userGoods"/>
        </p><br>

        <p>
            Уже зарегистрированы?<br><a href="<@spring.url value="/login"/>" class="to_register"> Войдите на сайт </a>
        </p>
    </@form.form>
</div>
</#macro>