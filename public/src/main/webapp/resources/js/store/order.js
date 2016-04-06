$(document).ready(function () {
    var $city = $("#city"),
        $street = $("#street"),
        $house = $("#house"),
        $flat = $("#flat"),
        $index = $("#index");

    $(document).on("click", ".address", function () {
        var $this = $(this);
        $city.val($this.data('city'));
        $street.val($this.data('street'));
        $house.val($this.data('house'));
        $flat.val($this.data('flat'));
        $index.val($this.data('index'));
    });
});