<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<html>
<head>
    <title></title>
</head>
<body>
login page


<form name='loginForm'
      action="/login" method='POST'>

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
            <td><input type="checkbox" name="remember"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"
                                   value="submit"/></td>
        </tr>
    </table>

<#if _csrf??>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</#if>
</form>
</body>
</html>
