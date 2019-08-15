



    $(document).ready(function () {
        $('#btn_submit').on("click",function (e) {
            e.preventDefault();

            var productName = $('#product_name').val();
            var  productPrice = $('#product_price').val();
           // var  userId = $('#user_id').val();
            var  productFile = $('#file_name').val();
            var  productImage = $('#product_image').val();
            var productDescription = $('#product_description').val();
            var productQuantity = $('#product_quantity').val();
            var productApprove = $('#product_approve').val();
            if($.trim(productName) === ""){
                alert("Product name cannot be empty");
            }
            else if(!$.trim($.isNumeric(productPrice))){
                alert("Price must be numeric");
            }
            else {
                var data = {};
                data["name"] = productName;
                data["description"] =  productDescription;
               // data["user.id"] = userId;
                data["fileName"] = productFile;
                data["price"] = productPrice;
                data["quantity"]=productQuantity;
                data["approve"]=productApprove;
                data["productImage"] = productImage;

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/saveproduct",
                    data: JSON.stringify(data),
                    dataType: 'json',
                    timeout: 6000,
                    success: function(data) {
                        if (confirm("Do you want the  product with Id " +data + " to be Saved?")){
                            window.location.reload();
                        }
                    },
                    error: function () {
                        alert("There is some error!");

                    }
                });
            }
        });
    });



