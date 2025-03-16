$(document).ready(function () {
    brandLowest.searchStart();
});

var brandLowest = {

    searchStart : function() {
        $.ajax({
            type:"get",
            url:"/api/product/brandLowest",
            success: function(response) {
                var brandName = response.data.brandName
                var dataList = response.data.productList;
                var total = response.data.totalPrice;

                var table = '';

                $.each(dataList, function(index, item) {
                    table += '<tr class="odd">'
                        + '<td>' + item.categoryName + '</td>'
                        + '<td>' + item.productPrice + '</td>'
                        + '<td>' + item.brandName + '</td>'
                        + '<td>' + item.productId + '</td>'
                        + '<td>' + item.productName + '</td>'
                        + '<td>' + item.categoryId + '</td>'
                        + '<td>' + item.brandId + '</td>'
                        + '</tr>';
                });
                table += '<tr class="odd"><td colspan="7"><h4 style="text-align: center">총액 : ' +total+'</h4></td>';
                $('#itemList').html(table);

                $('#brandName').html(brandName);
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