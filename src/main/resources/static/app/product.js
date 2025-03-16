$(document).ready(function () {
    product.searchStart();
});

var product = {

    searchStart : function() {
        $.ajax({
            type:"get",
            url:"/api/product",
            success: function(response) {
                var dataList = response.data;

                var table = '';

                $.each(dataList, function(index, item) {
                    var uri = "/pages/productEdit/" + item.productId;
                    table += '<tr class="odd" onclick="javascript:trOnClick(' + item.productId + ')" style="cursor:pointer;">'
                        + '<td>' + item.productId + '</td>'
                        + '<td>' + item.productName + '</td>'
                        + '<td>' + item.productPrice + '</td>'
                        + '<td>' + item.categoryId + '</td>'
                        + '<td>' + item.categoryName + '</td>'
                        + '<td>' + item.brandId + '</td>'
                        + '<td>' + item.brandName + '</td>'
                        + '<td>' + item.createdBy + '</td>'
                        + '<td>' + item.lastModifiedBy + '</td>'
                        + '<td>' + item.createdDate + '</td>'
                        + '<td>' + item.lastModifiedDate + '</td>'
                        + '</tr>';
                });

                $('#itemList').html(table);
            },
            error: function(e) {
                if(e.responseJSON.errors[0].detail != null) {
                    alert(e.responseJSON.errors[0].detail);
                } else {
                    alert("오류가 발생하였습니다! 잠시 후에 다시 시도해주세요.");
                }
            }
        });//ajax
    }
};

function trOnClick(productId) {
    location.href = "/pages/productEdit/" + productId;
}