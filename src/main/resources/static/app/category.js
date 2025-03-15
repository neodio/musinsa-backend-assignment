$(document).ready(function () {
    searchStart()
});

function searchStart() {
    $.ajax({
        type:"get",
        url:"/api/category",
        success: function(response) {
            var dataList = response.data;

            var table = '';

            $.each(dataList, function(index, item) {
                var uri = "/pages/categoryEdit/" + item.categoryId;
                console.log(uri);
                table += '<tr class="odd"><a href= " + uri + ">'
                    + '<td>' + item.categoryId + '</td>'
                    + '<td>' + item.categoryName + '</td>'
                    + '<td>' + item.createdBy + '</td>'
                    + '<td>' + item.lastModifiedBy + '</td>'
                    + '<td>' + item.createdDate + '</td>'
                    + '<td>' + item.lastModifiedDate + '</td>'
                    + '</a></tr>';
            });

            $('#itemList').html(table);
        },
        error: function() {
            alert('Error loading data');
        }
    });//ajax
}