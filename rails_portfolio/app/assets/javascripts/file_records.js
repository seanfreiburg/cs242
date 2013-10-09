$().ready(

    $.ajax({
        url: $('#file_link').attr('href'),
        context: document.body ,
        dataType: 'jsonp'
    }).success(function (results) {
            $('file_contents').append('blah');
        })

);