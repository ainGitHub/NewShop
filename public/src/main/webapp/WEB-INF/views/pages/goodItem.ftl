<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<@spring.url value="/resources/image/books/" var="img_book_res"/>
<#macro goodItem good showBtnCart showDelete showDeleteFromOrder>

<li>
    <a href="#modal${good.id}" class="open_modal"><img src="${img_book_res}${good.image}" alt=""/></a>

    <div class="product-info">
        <a href="<@spring.url value='/catalog/good?goodId=${good.id}'/>"><h3>${good.name}</h3></a>

        <div class="product-desc">
            <p>
            ${good.description}
            </p>
        </div>
    </div>


    <div class="add-cart">
        <strong class="price">Цена: ${good.price} руб.</strong>
        <!--показать добавить в корзину-->
        <#if showBtnCart>
            <a class="js_addToCart btn add-btn" data-id="${good.id}">
            Добавить В Корзину
            </a>
        </#if>


        <!--показать удалить из корзины-->
        <#if showDelete>
            <form method="post" action="<@spring.url value="/cart/delete"/>">
                <input name="goodId" hidden type="number" value="${good.id}">
                <input type="submit" value="Убрать из Корзины" class="btn delete-btn">
            </form>
            <label class="counter"> Колличество
                <input type="number" class="cartCountGood" data-id="${good.id}" style="width:60px;">
            </label>
        </#if>

        <#if showDeleteFromOrder>
            <form method="post" action="<@spring.url value="/order/delete/good"/>">
                <input name="goodId" hidden type="number" value="${good.id}">
                <input type="submit" value="Убрать" class="btn delete-btn">
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
        <a class="js_addToCart btn btn-cart" data-id="${good.id}">
            Добавить В Корзину
        </a>

    </#if>

</div>
</#macro>