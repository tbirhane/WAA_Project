(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

function review(clicked_id) {
    let id =clicked_id.split('-')[1];
    //var siblings = $('#'+clicked_id).siblings().not('img');


    let productUtil ={
        id:id,
        quantity:12,
        price:23

    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/buyer/products/detail',
        'data': JSON.stringify(productUtil),
        'dataType': 'json'

    }).done(reviewSuccess).fail(fail);


    function reviewSuccess(data){
       console.log(data);
       window.location = '/buyer/products/review';
    }
}

function followUser(clicked_id) {
    let id =clicked_id;
    // var siblings = $('#'+clicked_id).siblings().not('img');


    let product ={
        id:id,
        quantity:5,
        price:5

    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/buyer/follow',
        'data': JSON.stringify(product),
        'dataType': 'json'

    }).done(followSuccess).fail(fail);
    function  fail() {
        alert("fail")
    }

    function followSuccess(data){
        alert("success");
    }
}

