;
var siteUrl = "/test";

$(document).ready(function () {
    $(document).on('blur', '.cartCountGood', function () {
        var $this = $(this);
        if ($this.val().length == 0) return false;

        $.ajax({
            type: 'POST',
            url: siteUrl + '/cart/count',
            data: {
                goodId: $this.data('id'),
                count: $this.val()
            },
            success: function (status) {  // успешное завершение работы
                alert("count changed");
                console.log(siteUrl + '/cart/add result: data=' + status);
            },
            error: function () {    // На сервере произошла ошибка
                alert('Приносим извинения.' +
                    'На сервере произошла ошибка');
            }
        });
    });
});

// верно!!!
$(document).ready(function () {
    var $cartSum = $("#cartSum"),
        $cartCount = $("#cartCount");

    $(document).on('click', '.js_addToCart', function (event) {
        event.preventDefault();
        var $this = $(this);
        $.ajax({
            type: "POST",
            url: siteUrl + "/cart/add",
            data: {
                goodId: $this.data('id')
            }
        }).done(function (data) {
            $cartCount.text(data.cartGoodsCount);
            $cartSum.text(data.cartSum + "руб");


            $this.removeClass('js_addToCart').text('Перейти В Корзину').attr('href', siteUrl + '/cart');
        }).fail(function () {      // сюда приходит ответ если на сервере прооизошла ошибка
            alert('Приносим извинения.<br/>На сервере произошла ошибка');
        });
    });
});
