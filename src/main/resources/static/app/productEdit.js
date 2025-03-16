$(document).ready(function () {
    productEdit.category();
    productEdit.brand();
    productEdit.search();
});


var productEdit = {

    search : function() {
        var productId = $('#productId').val();

        $.ajax({
            url: '/api/product/' + productId,
            type: 'get',
            success: function(response) {
                var data = response.data;
                $('#productName').val(data.productName);
                $('#productPrice').val(data.productPrice);
                $('#category option[value="' + data.categoryId + '"]').prop('selected', true);
                $('#brand option[value="' + data.brandId + '"]').prop('selected', true);
                $('#createdBy').val(data.createdBy);
                $('#createdDate').val(data.createdDate);
                $('#lastModifiedBy').val(data.lastModifiedBy);
                $('#lastModifiedDate').val(data.lastModifiedDate);
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

    update : function() {
        var param = {
            productId: $('#productId').val(),
            productName: $('#productName').val(),
            productPrice: $('#productPrice').val(),
            categoryId: $('#category option:selected').val(),
            brandId: $('#brand option:selected').val()
        };

        $.ajax({
            url: '/api/product',
            type: 'put',
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function() {
                alert("수정되었습니다.");
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
    delete : function() {
        var productId = $('#productId').val();

        $.ajax({
            url: '/api/product/' + productId,
            type: 'delete',
            success: function() {
                alert("삭제되었습니다.");
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