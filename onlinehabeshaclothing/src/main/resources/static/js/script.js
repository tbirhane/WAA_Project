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

function addToCart(clicked_id) {
    let id =clicked_id;
    var siblings = $('#'+id).siblings().not('img');
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
            price:siblings[3].innerText
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/selectItem',
        'data': JSON.stringify(product),
        'dataType': 'json'

    }).done(saveToCart).fail(fail);
    updateCartCount();

    function saveToCart(data){
        console.log(data);
        $('#'+id).prop("disabled",true);
    }
}
// increment number of products in cart
function updateCartCount(){

    let counter = document.getElementById('item-count').textContent;
  //  console.log(counter);
    if(counter) {
        counter = parseInt(counter) + 1;
    }
    else
        counter=1;
    $('#item-count').text(counter);
}

//Create button upon change in input field
function createButton(id) {
    let parent = $('#'+id).parent();

    let updateButton = $('<button>').addClass("btn-info")
        .attr("id",'update-'+id)
        .attr("onclick", "updateQuantity(this.id)")
        .text("update");
    parent.append(updateButton);
}
//update the quantity to buy for each product
function updateQuantity(id) {
    let siblings = $('#'+id).siblings().not('img');

    let p_id = id.split('-')[1];
    let productUtil = {
        id:p_id,
        quantity:siblings[4].value,//.innerText,
        totalPrice:0
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/updateCart',
        'data': JSON.stringify(productUtil),
        'dataType': 'json'

    }).done(update).fail(fail);

    function update(data) {
        //update total price
        $('#total-price').text(data.totalPrice);
        //remove item if quantity is zero
        if(data.quantity<=0){
            let parent = $('#'+data.id).parent();
            parent.remove();
        }
        //edit quantity on view
        siblings[2].innerHTML = data.quantity;
        //show real quantity on input box
        siblings[4].value = data.quantity;
        //remove update button
        $('#'+id).remove();

    }

}

//shipping address submission
function shippingAddress(){
    let shippingAddress = JSON.stringify($('#shippingAddressForm').serializeFormJSON());
    console.log("initial add = "+shippingAddress);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/checkout',
        'data': shippingAddress,
        'dataType': 'json'

    }).done(addressSuccess).fail(fail);

    function addressSuccess(data) {
        console.log(data);
        $('#shippingAddressForm').remove();
        $('#paymentIfoForm').removeClass("hide");

    }
}

function processPayment() {
    let paymentInfo = JSON.stringify($('#paymentIfoForm').serializeFormJSON());
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/paymentInfo',
        'data': paymentInfo,
        'dataType': 'json'

    }).done(paymentSuccess).fail(fail);
    function paymentSuccess(data) {
        console.log("payment: "+ data);
        window.location = '/cart/orderConfirmation';
    }

}

function placeOrder() {
    let order = {
        id:1,
        orderDate: "11-12-13",
        orderStatus:"SHIPPED"
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/placeOrder',
        'data': JSON.stringify(order),
        'dataType': 'json'

    }).done(placeOrdeSuccess).fail(fail);
    function placeOrdeSuccess(data) {
        console.log(data);
        $('#placeOrderBtn').parent().append($('<p>').text("Thank you for shopping with us!"));
        $('#placeOrderBtn').parent().append($('<button>')
            .attr("id","downloadReceiptBtn")
            .attr("onclick", "downloadReceipt()")
            .addClass("btn btn-primary").text("Download Receipt"));
        $('#placeOrderBtn').parent().append($('<button>')
            .attr("id","orderHistoryBtn")
            .attr("onclick", "orderHistoryList()")
            .addClass("btn btn-primary").text("Order History"));
        $('#placeOrderBtn').remove();
    }
}

//cancel order

function cancelOrder(order_id){
    let order = {
        id:order_id,
        orderDate: $('#'+order_id).siblings()[1].innerText,
        orderStatus:$('#'+order_id).siblings()[2].innerText
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/cancelOrder',
        'data': JSON.stringify(order),
        'dataType': 'json'

    }).done(cancelOrderSuccess).fail(fail);
    function cancelOrderSuccess(data) {
        let p = $('#'+data.id).parent();
        if(data.orderStatus == 'SHIPPED'){
            p.append($('<p>').text("Product already Shipped!").addClass("red"));
            $('#'+data.id).prop("disabled",true);
        }else{

            p.remove();
        }

    }
}
function orderHistoryList() {
    let order = {
        id:1,
        orderDate: "12-12-12",
        orderStatus:"SHIPPED"
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url':'/cart/ordersHistory',
        'data': JSON.stringify(order),
        'dataType': 'json'

    }).done(orderHistorySuccess).fail(fail);
    function orderHistorySuccess(data) {
        window.location="/cart/orderHistoryList";
    }
}

function downloadReceipt() {
    console.log("*************** Download ****************");
    let specialElementHandlers = {
        "#editor": function (element, renderer) {
            return true;
        }
    };
    let doc = new jsPDF();
    console.log(doc);
    doc.fromHTML($('#main-order-confirmation').html(), 15, 15, {
        "width": 150,
        "elementHandlers": specialElementHandlers
    });
    doc.save('order-receipt.pdf');
    console.log("doc = "+doc);
}

function  fail() {
    alert("fail");
}

//on page load
$(function () {
    $('#address-btn').click(shippingAddress);
    $('#payment-btn').click(processPayment);
    $('#placeOrderBtn').click(placeOrder);
});