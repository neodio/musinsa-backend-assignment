$(document).ready(function () {
});

var minMaxProduct = {

    searchStart : function() {

        var param = $('#categoryName').val();

        $.ajax({
            type:"get",
            url:"/api/product/findProductByCategoryName?categoryName=" + param,
            success: function(response) {
                var categoryName = response.data.categoryName
                var lowestProductList = response.data.lowestProductList;
                var highestProductList = response.data.highestProductList;

                $('#categoryName').html(categoryName);

                var lowestProductListTable = '';
                $.each(lowestProductList, function(index, item) {
                    lowestProductListTable += '<tr class="odd">'
                        + '<td>' + item.brandName + '</td>'
                        + '<td>' + item.productPrice + '</td>'
                        + '<td>' + item.categoryName + '</td>'
                        + '<td>' + item.productId + '</td>'
                        + '<td>' + item.productName + '</td>'
                        + '<td>' + item.categoryId + '</td>'
                        + '<td>' + item.brandId + '</td>'
                        + '</tr>';
                });
                $('#lowestProductList').html(lowestProductListTable);

                var highestProductListTable = '';

                $.each(highestProductList, function(index, item) {
                    highestProductListTable += '<tr class="odd">'
                        + '<td>' + item.brandName + '</td>'
                        + '<td>' + item.productPrice + '</td>'
                        + '<td>' + item.categoryName + '</td>'
                        + '<td>' + item.productId + '</td>'
                        + '<td>' + item.productName + '</td>'
                        + '<td>' + item.categoryId + '</td>'
                        + '<td>' + item.brandId + '</td>'
                        + '</tr>';
                });
                $('#highestProductList').html(highestProductListTable);

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