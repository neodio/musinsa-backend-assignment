$(document).ready(function () {
    categoryEdit.search();
});


var categoryEdit = {

    search : function() {
        var categoryId = $('#categoryId').val();

        $.ajax({
            url: '/api/category/' + categoryId,
            type: 'get',
            success: function(response) {
                var data = response.data;
                $('#categoryName').val(data.categoryName);
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
            categoryId: $('#categoryId').val(),
            categoryName: $('#categoryName').val()
        };

        $.ajax({
            url: '/api/category',
            type: 'put',
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function() {
                alert("수정되었습니다.");
                location.replace("/pages/category");
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
        var categoryId = $('#categoryId').val();

        $.ajax({
            url: '/api/category/' + categoryId,
            type: 'delete',
            success: function() {
                alert("삭제되었습니다.");
                location.replace("/pages/category");
            },
            error: function(e) {
                if(e.responseJSON.errors[0].detail != null) {
                    alert(e.responseJSON.errors[0].detail);
                } else {
                    alert("오류가 발생하였습니다! 잠시 후에 다시 시도해주세요.");
                }
            }
        });
    }
};