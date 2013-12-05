function PokerApi(url) {
    this.url = url

}


PokerApi.prototype.send_move = function (key, move, amount) {
    var result = null;
    $.ajax({
            async: false,
            url: this.url + "/send_move?key=" + key + "&move=" + move + "&amount=" + amount,
            datatype: "json",
            crossDomain: true
        }
    ).done(function (data) {
            result = data;
        });
    return result;
};

PokerApi.prototype.get_key = function (name) {
    var result = null;
    $.ajax({
            async: false,
            url: this.url + "/get_key?name=" + name,
            datatype: "json",
            crossDomain: true
        }
    ).done(function (data) {
            result = data;
        });
    return result;
};


PokerApi.prototype.get_status = function (key) {
    var result = null;
    $.ajax({
            async: false,
            url: this.url + "/get_status?key=" + key,
            datatype: "json",
            crossDomain: true
        }
    ).done(function (data) {
            result = data;
        });
    return result;
};



