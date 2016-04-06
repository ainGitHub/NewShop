<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
Вы только что заказали товары на нашем сайте<br>
<#if cartGoods??>
    <#list cartGoods as good>
    Товар: ${good.good.name}
    В колличестве: ${good.count}
    Цена за шт: ${good.good.price}
    Сумма: ${good.good.price * good.count}
    <br>
    </#list>
</#if>

</body>
</html>