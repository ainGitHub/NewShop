<#include "../templates/main.ftl">
<@mainTemplate />
<#macro body>
<style xmlns="http://www.w3.org/1999/html">
    .btn-cart {
        padding: 6px 10px;
        margin-top: 3%;
        margin-left: 30%;
        width: 160px;
        height: 30px;
        font-size: 12px;
        line-height: 14px;
        text-transform: uppercase;
        background: #8b0000;
        color: #fff;
    }

    .btn-cart:hover {
        background: green;
    }
</style>


<div class="products">
    <#if allGoods??>
    <ul>
        <#list allGoods as good>
            <li>
                <a href="#"><img src="/resources/image/big1.jpg" alt=""/></a>

                <div class="product-info">
                    <h3>${good.name}</h3>

                    <div class="product-desc">
                        <h4>WOMEN’S</h4>

                        <p>${good.description}</p>
                        <strong class="price">${good.price}</strong>
                    </div>

                    <form>
                        <input type="hidden" value="1" name="goodId">
                        <button type="submit" class="btn-cart">Добавить В Корзину</button>
                    </form>
                </div>
            </li>
        </#list>
    </ul>
    </#if>
</div>
</#macro>