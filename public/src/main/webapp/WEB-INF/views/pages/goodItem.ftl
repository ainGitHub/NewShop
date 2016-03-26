<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<@spring.url value="/resources/image/books/" var="img_book_res"/>
<#macro goodItem good showBtnCart showDelete>

<li>
    <img src="${img_book_res}${good.image}" alt=""/>

    <div class="product-info">
        <a href="#modal${good.id}" class="open_modal"><h3>${good.name}</h3></a>

        <div class="product-desc">
            <p>
            ${good.description}
            </p>
        </div>
    </div>


    <div class="add-cart">
        <strong class="price">Цена: ${good.price} руб.</strong>
        <#if showBtnCart>
            <a class="js_addToCart btn-cart" data-id="${good.id}">
            Добавить В Корзину
            </a>
        </#if>

        <#if showDelete>
            <form method="post" action="<@spring.url value="/cart/delete"/>">
                <input name="goodId" hidden="${good.id}" type="number">
                <input type="submit" value="Убрать из Корзины" class="btn-cart">
            </form>
        </#if>
    </div>
</li>

<div id="modal${good.id}" class="modal_form"> <!-- скрытый див с уникaльным id = modalid -->
    <span class="modal_close">X</span><br>
    <img src="${img_book_res}${good.image}"/>

    <h2>${good.name}</h2>

    <p>
    ${good.description}
    </p>

    <strong class="price">Цена: ${good.price} руб.</strong>
    <#if showBtnCart>
        <a class="js_addToCart btn-cart" data-id="${good.id}">
            Добавить В Корзину
        </a>

    </#if>

</div>
</#macro>