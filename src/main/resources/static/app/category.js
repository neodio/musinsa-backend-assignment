$(document).ready(function () {
    category.init();
    category.searchStart();
});

var category = {
    /**
     * 초기화
     */
    init : function() {
    },

    searchStart : function() {
        $.ajax({
            type:"get",
            url:"/api/category",
            success: function(response) {
                var dataList = response.data;

                var table = '';

                $.each(dataList, function(index, item) {
                    var uri = "/pages/categoryEdit/" + item.categoryId;
                    table += '<tr class="odd" onclick="javascript:trOnClick(' + item.categoryId + ')" style="cursor:pointer;">'
                        + '<td>' + item.categoryId + '</td>'
                        + '<td>' + item.categoryName + '</td>'
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

function trOnClick(categoryId) {
    location.href = "/pages/categoryEdit/" + categoryId;
}