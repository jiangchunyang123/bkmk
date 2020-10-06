$(document).ready(function () {
    var bookPublisherType = "BOOK_PUBLISHER";
    $.get("/api/v1/dicts", function (r) {
        console.log("dict result:", JSON.stringify(r));
        if (r.length > 0) {
            console.log("dict1 result:", JSON.stringify(r));

            let publisherSelect = $("#book_publisher");
            for (var dict in r) {
                console.log("dict--" + r[dict]);
                console.log("dictType=" + r[dict].type + " bookPublisherType===dict.type " + (bookPublisherType === r[dict].type));
                if (bookPublisherType === r[dict].type) {
                    console.log("dict2 result:", JSON.stringify(r));
                    publisherSelect.append(" <option value=\"" + r[dict].code + "\">" + r[dict].value + "</option>")
                }
            }
        }

    });

    $('#bookList').bootstrapTable({
        columns: [{
            field: 'id',
            title: 'Id'
        }, {
            field: 'name',
            title: '书名'
        }, {
            field: 'process',
            title: '进度'
        }, {
            field: 'create_time',
            title: '录入时间'
        }, {
            field: 'pageNumber',
            title: '页数'
        }],
        onLoadSuccess: function () {
            $('#book_code').focus();
        }
    })
    $('#book_code').focus();

});