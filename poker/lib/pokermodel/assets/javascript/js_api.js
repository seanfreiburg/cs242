function PokerApi(url) {
    this.url = url

}


PokerApi.prototype.send_move = function (key, move, amount) {
    return $.ajax({
            async: false,
            url: this.url + "/send_move?key=" + key + "&move=" + move + "&amount=" + amount,
            datatype: "json"
        }
    ).done(function (data) {
            return data
        })
};

PokerApi.prototype.get_key = function (name) {
    return $.ajax({
            async: false,
            url: this.url + "/get_key?name=" + name,
            datatype: "json"
        }
    ).done(function (data) {
            return data
        })
};




PokerApi.prototype.get_status = function (key) {
    return $.ajax({
            async: false,
            url: this.url + "/get_status?key=" + key,
            datatype: "json"
        }
    ).done(function (data) {
            return data
        })
};



