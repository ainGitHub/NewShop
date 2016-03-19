<#macro goodItem good showBtnCart>

<li href="#modal${good.id}" class="open_modal">
    <img src="/resources/image/books/${good.image}" alt=""/>

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
        <#if showBtnCart>
        <button class="js_addToCart btn-cart" data-id="${good.id}" onclick="return false;">
            Добавить В Корзину
        </button>
        </#if>
    </div>
</li>

<div id="modal${good.id}" class="modal_form"> <!-- скрытый див с уникaльным id = modalid -->
    <span class="modal_close">X</span><br>
    <img src="/resources/image/books/${good.image}"/>

    <h2>${good.name}</h2>

    <p>
    ${good.description}
    </p>

    <strong class="price">Цена: ${good.price} руб.</strong>
    <#if showBtnCart>
        <button class="js_addToCart btn-cart" data-id="${good.id}" onclick="return false;">
            Добавить В Корзину
        </button>
    </#if>

</div>
</#macro>