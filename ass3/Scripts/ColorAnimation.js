$(function () {
    var state = true;
    $("#button").click(function () {
        if (state) {
            $("#effect").animate({
                backgroundColor:"black",
                color: "#fff",
                width: 500
            }, 1000);
        } else {
            $("#effect").animate({
                backgroundColor: "white",
                color: "#000",
                width: 240
            }, 1000);
        }
        state = !state;
    });
});