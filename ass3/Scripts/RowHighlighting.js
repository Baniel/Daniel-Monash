$(document).ready(function () {

    $('tr').mouseover(function () {
        $(this).addClass('dataHover');
    });
    $('tr').mouseout(function () {
        $(this).removeClass('dataHover');
    });
});