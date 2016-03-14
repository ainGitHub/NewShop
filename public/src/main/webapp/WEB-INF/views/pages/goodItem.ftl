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

    <#--<a href="/cart/add" class="js_addToCart" data-id="${good.id}">
        <button class="js_addToCart btn-cart button">Добавить В Корзину</button>
    </a>-->
    </div>
</li>
</#macro>