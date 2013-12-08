function PokerApi(url) {
    this.url = url

}


PokerApi.prototype.send_move = function (key, move, amount, callback) {
    $.ajax({
            async: true,
            url: this.url + "/send_move?key=" + key + "&move=" + move + "&amount=" + amount,
            datatype: "json"
        }
    ).done(function (data) {
            callback(data);
        });
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
    console.log(result);

    return result;
};


PokerApi.prototype.get_status = function (key,callback) {
    $.ajax({
            async: true,
            url: this.url + "/get_status?key=" + key,
            datatype: "json"
        }
    ).done(function (data) {
            callback(data);
        });
};



