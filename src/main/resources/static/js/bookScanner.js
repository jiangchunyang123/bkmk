$(document).ready(function () {
    $.validator.setDefaults({
        submitHandler: function () {
            alert( "Form successful submitted!" );
        }
    });

    var bookPublisherType = "BOOK_PUBLISHER";
    $.ajax( {
        url:'http://localhost:8090/api/v1/dicts',
        dataType: 'json',
        success: function (data) {
            var dicts = [];
            for(var i in data){
                dicts.push({
                    id:data[i].code,
                    text:data[i].value
                });
                publisherDicts[data[i].code] = data[i].value;
            }
            $("#book_publisher").select2({
                data:dicts,
                language: "zh-CN"
            });
        }
    });

    let book_code = $('#book_code');
    let publisherDicts = {};

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
            console.log("formatStr="+formatStr);
            for (let dict in publisherDicts) {
                console.log("dict="+dict);
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
            console.log("publishCode="+publisherCode);

            var param = {
                pageNum: 0,
                pageSize: 10,
                barCode:result
            };
            $.ajax({
                url: "/api/v1/books",
                data: param,
                type: "GET",
                dataType: "json",
                success: function (r) {
                    console.log("r="+JSON.stringify(r));
                    if(r.data.content !=null&&r.data.content.length>0){
                        console.log("hello+"+JSON.stringify(r.data.content)+"-->length="+r.data.content.length);
                        alert("该条形码已存在="+result);
                        resetForm();
                        book_code.focus();
                        return;
                    }
                    console.log("hello1"+r.data.content.length);
                    $("#book_publisher").val(publisherCode).trigger("change");
                }
            });

        }
    });

   var bookScannerTable =  $('#bookScanHistory').DataTable({
        paging: true,
        serverSide: true,
        pageLength: 10,
        initComplete: function (setting, json) {
            book_code.focus();
            console.log("bookCode init success!");
        },
        ajax: function (data, callback, settings) {
            console.log("data=" + JSON.stringify(data));
            var param = {
                pageNum: data.start / data.length,
                pageSize: data.length
            };
            $.ajax({
                url: "/api/v1/books",
                data: param,
                type: "GET",
                dataType: "json",//返回数据格式为json
                success: function (r) {
                    var resultData = {};
                    resultData.recordsTotal = r.data.totalElements;//totalCount指的是总记录数
                    resultData.recordsFiltered = r.data.totalElements;//后台不实现过滤功能,全部的记录数都需输出到前端，记录数为总数
                    resultData.data = r.data.content;
                    callback(resultData);
                }
            });
        },
        bFilter: true, //去掉搜索框方法
        bLengthChange: true,//也就是页面上确认是否可以进行选择一页展示多少条
        dom: '<"row"<"col-sm-9"><"col-sm-3 form-inline"f>>rt<"row"<"col-sm-6"i><"ml-auto form-inline"l><"ml-auto"p>>',
        columns: [{
            data: 'barCode',
            title: '条码'
        }, {
            data: 'name',
            title: '书名'
        }, {
            data: 'publisherName',
            title: '出版社'
        }, {
            data: 'createTime',
            title: '录入时间',
            formatter: function (val, row) {
                return datefomate(val);
            }
        }, {
            data: 'pageNumber',
            title: '页数'
        }]
    });


    function submitForm() {
        var bookCode = book_code.val();
        var bookName = $('#book_name').val();
        var bookPageNumber = $('#book_pageNumber').val();
        var author = $('#book_author').val();
        var publisher = $('#book_publisher').val();
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
                if (r.state=='0') {
                    // $('#saveSucess').html('操作成功').addClass('alert-success').show().delay(1000).fadeOut();
                    resetForm();
                    bookScannerTable.ajax.reload();
                } else {
                    alert(r.message);
                }
            }
        });
    }
    function resetForm(){
        book_code.val('');
        $('#book_name').val('');
        $('#book_pageNumber').val('');
        $('#book_author').val('');
        $("#book_publisher").val('').trigger("change");
    }

    $('#bookScannerForm').validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            password: {
                required: true,
                minlength: 5
            },
            terms: {
                required: true
            },
        },
        messages: {
            email: {
                required: "Please enter a email address",
                email: "Please enter a vaild email address"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long"
            },
            terms: "Please accept our terms"
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        },
        submitHandler:function(){
            submitForm();
            return true;
        }
    });


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