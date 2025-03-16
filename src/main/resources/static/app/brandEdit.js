$(document).ready(function () {
    brandEdit.search();
});


var brandEdit = {

    search : function() {
        var brandId = $('#brandId').val();

        $.ajax({
            url: '/api/brand/' + brandId,
            type: 'get',
            success: function(response) {
                var data = response.data;
                $('#brandName').val(data.brandName);
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
            brandId: $('#brandId').val(),
            brandName: $('#brandName').val()
        };

        $.ajax({
            url: '/api/brand',
            type: 'put',
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function() {
                alert("수정되었습니다.");
                location.replace("/pages/brand");
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
        var brandId = $('#brandId').val();

        $.ajax({
            url: '/api/brand/' + brandId,
            type: 'delete',
            success: function() {
                alert("삭제되었습니다.");
                location.replace("/pages/brand");
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