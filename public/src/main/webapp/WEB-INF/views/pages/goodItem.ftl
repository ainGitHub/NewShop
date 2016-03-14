<#macro goodItem good>
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

        <button class="js_addToCart btn-cart" data-id="${good.id}" onclick="return false;">
            Добавить В Корзину
        </button>
    </div>
</li>
</#macro>