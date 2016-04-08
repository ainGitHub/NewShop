$(document).ready(function () {

    $("#back-top").hide();

    // fade in #back-top
    $(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 100) {
                $('#back-top').fadeIn();
            } else {
                $('#back-top').fadeOut();
            }
        });

        $('#back-top').click(function () {
            $('body,html').animate({
                scrollTop: 0
            }, 600);
            return false;
        });
    });

});