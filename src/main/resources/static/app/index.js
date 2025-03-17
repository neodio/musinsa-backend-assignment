$(document).ready(function () {
    product.categoryCount();
    product.brandCount();
    product.productCount();
});

var product = {

    categoryCount : function() {
        $.ajax({
            type:"get",
            url:"/api/product/count",
            success: function(response) {
                var data = response.data;

                $('#categoryCount').html(data);
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
    brandCount : function() {
        $.ajax({
            type:"get",
            url:"/api/brand/count",
            success: function(response) {
                var data = response.data;

                $('#brandCount').html(data);
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
    productCount : function() {
        $.ajax({
            type:"get",
            url:"/api/product/count",
            success: function(response) {
                var data = response.data;

                $('#productCount').html(data);
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