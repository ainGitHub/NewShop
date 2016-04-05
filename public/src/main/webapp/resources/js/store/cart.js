;
var siteUrl = "/test";

$(document).ready(function () {
    var $cartSum = $("#cartSum"),
        $cartCount = $("#cartCount");

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
            success: function (data) {  // успешное завершение работы
                $cartCount.text(data.cartGoodsCount);
                $cartSum.text(data.cartSum + "руб");
            },
            error: function () {    // На сервере произошла ошибка
                alert('Приносим извинения.' +
                    'На сервере произошла ошибка');
            }
        });
    });

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
            if (data.exist != null) {
                $cartCount.text(data.cartGoodsCount);
                $cartSum.text(data.cartSum + "руб");
                $this.removeClass('js_addToCart').text('Перейти В Корзину').attr('href', siteUrl + '/cart');
            } else {
                $this.text('Уже в корзине');
            }
        }).fail(function () {      // сюда приходит ответ если на сервере прооизошла ошибка
            alert('Приносим извинения.<br/>На сервере произошла ошибка');
        });
    });
});

