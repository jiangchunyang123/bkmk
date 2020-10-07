$(document).ready(function () {
    var bookPublisherType = "BOOK_PUBLISHER";
    let publisherSelect = $("#book_publisher");
    let book_code = $('#book_code');
    let publisherDicts = {};

    function addFunctionAlty(value, row, index) {
        return [
            '<button id="bind" type="button" class="btn btn-sm">绑定</button>'
        ].join('');
    }

    window.operateEvents = {
        'click #bind': function (e, value, row, index) {
            alert(row.qxxh);
            $("#upload").modal('show');
        }, 'click #unbind': function (e, value, row, index) {
            alert(row.qxxh);
            $("#upload").modal('show');
        }
    };
    book_code.keydown(function () {
        if (event.keyCode == "13") {
            let result = "978-7";
            let val = book_code.val();
            if (!val.startsWith("9787")) {
                alert("暂不支持非中国书籍");
                return;
            }
            let formatStr = val.substr(4, val.length);
            let publisherCode = "";
            for (let dict in publisherDicts) {
                if (formatStr.startsWith(dict)) {
                    result += ("-" + dict);
                    formatStr = formatStr.replace(dict, "");
                    publisherCode = dict;
                    break;
                }
            }

            result += ("-" + formatStr.slice(0, formatStr.length - 1) + "-" +
                formatStr.slice(formatStr.length - 1));
            book_code.val(result);
            publisherSelect.selectpicker('val', publisherCode);
        }
    });
    $.get("/api/v1/dicts", function (r) {
        if (r.length > 0) {
            publisherSelect.selectpicker();
            let deviceStr = "";
            for (var dict in r) {
                if (bookPublisherType === r[dict].type) {
                    deviceStr += "<option  data-icon='glyphicon glyphicon-heart' value='"
                        + r[dict].code + "'> " + r[dict].value + "</option>";
                    publisherDicts[r[dict].code] = r[dict].value;
                }
            }
            publisherSelect.append(deviceStr);
            publisherSelect.selectpicker('refresh');
        }

    });


    $('#bookList').bootstrapTable({
        url: '/api/v1/books',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        // toolbar: '#toolbar',                //工具按钮用哪个容器
        // striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: function (params) {
            console.log("params={}" + JSON.stringify(params));
            var result = {
                pageNum: params.offset / params.limit,
                pageSize: params.limit,
            };
            return result;
        },//传递参数（*）
        theadClasses: "thead-light",//这里设置表头样式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
        // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        // strictSearch: true,
        // showColumns: true,                  //是否显示所有的列
        // showRefresh: true,                  //是否显示刷新按钮
        // minimumCountColumns: 2,             //最少允许的列数
        // clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        // showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        responseHandler: function (res) {
            return {
                rows: res.data.content,
                total: res.data.totalElements
            }
        },
        rowStyle: function (row, index) {
            return {
                css: {
                    padding: "5px;"
                }
            }
        },
        columns: [{
            field: 'barCode',
            title: '条码'
        }, {
            field: 'name',
            title: '书名'
        }, {
            field: 'publisherName',
            title: '出版社'
        }, {
            field: 'createTime',
            title: '录入时间',
            formatter: function (val, row) {
                return datefomate(val);
            }
        }, {
            field: 'pageNumber',
            title: '页数'
        }],
        onLoadSuccess: function () {
            book_code.focus();
        }
    });

    $('#bookForm').validate({
        submitHandler: function (form) {
            submitForm();
        },
        debug: false
    });

    function submitForm() {
        var bookCode = book_code.val();
        var bookName = $('#book_name').val();
        var bookPageNumber = $('#book_pageNumber').val();
        var author = $('#book_author').val();
        var publisher = publisherSelect.selectpicker('val');
        var param = {
            barCode: bookCode,
            name: bookName,
            pageNumber: bookPageNumber,
            author: author,
            publisher: publisher
        };
        $.ajax({
            url: "/api/v1/books",
            type: "POST",
            data: JSON.stringify(param),
            dataType: "json",
            contentType: "application/json",
            success: function (r) {
                // $('#saveSucess').alert();
                if (r.success) {
                    $('#saveSucess').html('操作成功').addClass('alert-success').show().delay(1000).fadeOut();
                    book_code.val('');
                    $('#book_name').val('');
                    $('#book_pageNumber').val('');
                    $('#book_author').val('');
                    publisherSelect.selectpicker('val', '');
                    refreshTable();
                } else {
                    alert(r.message);
                }

            }
        });
    }

    function refreshTable() {
        $('#bookList').bootstrapTable('refresh');
    }

    book_code.focus();

    //格式华日期为2017-12-20
    function datefomate(value) {
        if (value == null || value == undefined) {
            return "";
        }
        var date = new Date(value);

        Y = date.getFullYear(),
            m = date.getMonth() + 1,
            d = date.getDate(),
            H = date.getHours(),
            i = date.getMinutes(),
            s = date.getSeconds();
        return Y + '-' + m + '-' + d + ' ' + H + ':' + i;
    };
});