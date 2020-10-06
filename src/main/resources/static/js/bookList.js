$(document).ready(function () {

    var table = $('#bookList');

    table.grid({
            id: "bookList",
            data: function () {

            },
            trFormatter: function (tr, rowData) {
                if (rowData.state === 1) {
                    tr.addClass("success");
                } else if (rowData.state === -1) {
                    tr.addClass("danger");

                } else if (rowData.state === 0) {
                    tr.addClass("info");
                }
            },
            columns:
                [
                    {field: 'ck', checkbox: true},
                    {field: 'index', title: '序号', width: 100, align: 'left'},
                    {field: 'schedule.title', title: '计划内容', width: 100, align: 'left'},
                    {field: 'remark', title: '备注', width: 50, align: 'left'},
                    {field: 'deadLine', title: '最后期限', width: 50, align: 'left'},
                    {
                        field: 'state', title: '状态', width: 50, align: 'left', formatter: function (row, data) {
                            if (data === 1) {
                                return "完成";
                            } else if (data === -1) {
                                return "放弃";
                            } else {
                                return "待办";
                            }

                        }
                    }
                ],
            isoddcolor:
                false,
            pagination:
                false,
            searchnation:
                false,
            pagesize:
                20
        }
    );

});