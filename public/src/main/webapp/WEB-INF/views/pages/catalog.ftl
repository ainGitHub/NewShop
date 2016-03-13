<#include "../templates/main.ftl">
<@mainTemplate/>
<#macro body>
<div class="products">
    <#if goods??>
        <ul id="goodList">
            <#list goods as good>
            <li>
                <a href="#"><img src="/resources/image/books/${good.image}" alt=""/></a>

                <div class="product-info">
                    <a href="#"><h3>${good.name}</h3></a>

                    <div class="product-desc">
                        <p>
                        ${good.description}
                        </p>
                    </div>

                </div>
                <div class="add-cart">
                    <strong class="price">Цена: ${good.price} руб.</strong>

                    <form>
                        <input type="hidden" value="1" name="goodId">
                        <button type="submit" class="btn-cart">Добавить В Корзину</button>
                    </form>
                </div>
            </li>
        </#list>
            <li class="last">
                <#list pages as p >
                    <a href="/catalog?page=${p}">
                        <button> ${p}</button>
                    </a>
                </#list>

                all goods (${goodsCount})
            </li>
    </ul>

    </#if>
</div>
</#macro>