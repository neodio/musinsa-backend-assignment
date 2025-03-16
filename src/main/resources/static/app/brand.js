$(document).ready(function () {
    brand.init();
    brand.searchStart();
});

var brand = {
    /**
     * 초기화
     */
    init : function() {
    },

    searchStart : function() {
        $.ajax({
            type:"get",
            url:"/api/brand",
            success: function(response) {
                var dataList = response.data;

                var table = '';

                $.each(dataList, function(index, item) {
                    var uri = "/pages/brandEdit/" + item.brandId;
                    table += '<tr class="odd" onclick="javascript:trOnClick(' + item.brandId + ')" style="cursor:pointer;">'
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

function trOnClick(brandId) {
    location.href = "/pages/brandEdit/" + brandId;
}