$(document).ready(function () {
    productAdd.category();
    productAdd.brand();
});

var productAdd = {
    add : function() {

        var param = {
            productName: $('#productName').val(),
            productPrice: $('#productPrice').val(),
            categoryId: $('#category option:selected').val(),
            brandId: $('#brand option:selected').val()
        };

        $.ajax({
            url: '/api/product',
            type: 'post',
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function() {
                alert("등록되었습니다.");
                location.replace("/pages/product");
            },
            error: function(e) {
                if(e.responseJSON.errors[0].detail != null) {
                    alert(e.responseJSON.errors[0].detail);
                } else {
                    alert("오류가 발생하였습니다! 잠시 후에 다시 시도해주세요.");
                }
            }
        });
    },

    category : function() {
        $.ajax({
            type:"get",
            url:"/api/category",
            success: function(response) {
                var dataList = response.data;
                var option = '';
                $.each(dataList, function(index, item) {
                    option += '<option value=' + item.categoryId + '>' + item.categoryName + '</option>';
                });

                $('#category').html(option);
            },
            error: function(e) {
                if(e.responseJSON.errors[0].detail != null) {
                    alert(e.responseJSON.errors[0].detail);
                } else {
                    alert("오류가 발생하였습니다! 잠시 후에 다시 시도해주세요.");
                }
            }
        });//ajax
    },

    brand : function() {
        $.ajax({
            type:"get",
            url:"/api/brand",
            success: function(response) {
                var dataList = response.data;
                var option = '';
                $.each(dataList, function(index, item) {
                    option += '<option value=' + item.brandId + '>' + item.brandName + '</option>';
                });

                $('#brand').html(option);
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