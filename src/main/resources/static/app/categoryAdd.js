var categoryAdd = {
    add : function() {
        var param = {
            categoryName: $('#categoryName').val()
        };

        $.ajax({
            url: '/api/category',
            type: 'post',
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function() {
                alert("등록되었습니다.");
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