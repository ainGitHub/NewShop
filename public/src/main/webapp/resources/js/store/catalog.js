$(document).ready(function () {

    $(document).on('click', '#showMore', function () {
        var $this = $(this);
        var page = $this.data('page'),
            limit = $this.data('limit');
        $.ajax({
            type: "POST",
            url: "/showMore",
            data: {
                page: page,
                limit: limit
            }
        }).done(function (data) {  // сюда приходит ответ при успехе
            console.log(data);
            if (data != '') {
                $("#goodList").append(data);
                updateCounter();
            } else {
                $this.hide();
            }
        }).fail(function () {      // сюда приходит ответ если на сервере прооизошла ошибка
            alert("fail");
            $this.hide();
        });

        function updateCounter() {
            $this.data('page', page + 1);
            var $goodsCount = $('#goodsCount');
            var goodsCount = parseInt($goodsCount.text());
            if (goodsCount > limit) {
                $goodsCount.text(goodsCount - limit);
                $('#limit').text(Math.min(limit, goodsCount - limit))
            } else {
                $this.hide();
            }
        }
    });


    $(document).on('click', '.js_goodDetail', function () {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/good/' + $(this).data('id'),
            //dataType: 'json',
            success: function (data, status) {  // успешное завершение работы
                alert(JSON.stringify(data, "", 4));
            },
            error: function (error) {    // На сервере произошла ошибка
                alert('Приносим извинения.<br/>На сервере произошла ошибка<br/>' + error);
            }
        });
    });

});