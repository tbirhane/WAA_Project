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

function approveProduct(clicked_id) {
    let id =clicked_id.split('-')[1];
    var siblings = $('#'+clicked_id).siblings().not('img');
    // var orderline = {
    //     id:id,
    //     product:{
    //         id:id,
    //         name:siblings[0].innerText,
    //         desciption: siblings[1].innerText,
    //          quantity:siblings[2].innerText,
    //         price:siblings[3].innerText
    //     },
    //     quantity:siblings[4].value
    // }

    let product ={
        id:id,
        name:siblings[0].innerText,
        description: siblings[1].innerText,
        quantity:siblings[2].innerText,
        price:siblings[3].innerText,
        approve:true
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/admin/products/approve',
        'data': JSON.stringify(product),
        'dataType': 'json'

    }).done(removeProduct).fail(fail);

    function fail() {
        alert("fail");
    }

    function removeProduct(data){
        let id =data.id;
        let p = $("#approve-"+id).parent();
        p.remove();
    }
}

function rejectProduct(clicked_id) {
    let id =clicked_id.split('-')[1];
    var siblings = $('#'+clicked_id).siblings().not('img');
    // var orderline = {
    //     id:id,
    //     product:{
    //         id:id,
    //         name:siblings[0].innerText,
    //         desciption: siblings[1].innerText,
    //          quantity:siblings[2].innerText,
    //         price:siblings[3].innerText
    //     },
    //     quantity:siblings[4].value
    // }

    let product ={
        id:id,
        name:siblings[0].innerText,
        description: siblings[1].innerText,
        quantity:siblings[2].innerText,
        price:siblings[3].innerText,
        approve:true
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/admin/products/reject',
        'data': JSON.stringify(product),
        'dataType': 'json'

    }).done(removeProduct).fail(fail);

    function fail() {
        alert("fail");
    }

    function removeProduct(data){
        let id =data.id;
        let p = $("#reject-"+id).parent();
        p.remove();
    }
}

