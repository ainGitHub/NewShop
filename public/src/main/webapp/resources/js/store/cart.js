/**
 * Created by ainur on 04.03.2016.
 */
$(document).ready(function () {
    $(document).on('blur', '.cartCountGood', function () {
        event.preventDefault();
        var $this = $(this);
        if ($this.val().length == 0) return false;
        $.ajax({
            type: 'POST',
            url: '/cart/changeCount',
            data: {
                goodId: $this.data('id'),
                count: $this.val()
            },
            success: function (status) {  // успешное завершение работы
                console.log('/cart/add result: data=' + status);
            },
            error: function () {    // На сервере произошла ошибка
                alert('Приносим извинения.<br/>На сервере произошла ошибка');
            }
        });
    });
});