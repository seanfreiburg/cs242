function PokerApi(url) {
    this.url = url

}


PokerApi.prototype.send_move = function (key, move, amount) {
    $.ajax({
            async: false,
            url: this.url + "/send_move?key=" + key + "&move=" + move + "&amount=" + amount,
            datatype: "json"
        }
    ).done(function (data) {
            return data
        })
}

PokerApi.prototype.get_key = function (name) {
    $.ajax({
            async: false,
            url: this.url + "/get_key?name=" + name,
            datatype: "json"
        }
    ).done(function (data) {
            return data
        })
}




PokerApi.prototype.get_status = function (key) {
    $.ajax({
            async: false,
            url: this.url + "/get_status?key=" + key,
            datatype: "json"
        }
    ).done(function (data) {
            return data
        })
};



