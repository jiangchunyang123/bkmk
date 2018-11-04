$(document).ready(function () {
    var querySchUrl = "/sch/rcd";
    var $newBtn = $('.newPlan');
    var $saveBtn = $('.save');
    var $form = $('.form-horizontal');
    var table = $('#mainTable');
    $("#firstDeadLine").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        // autoclose: true,
        todayBtn: true,
        keyboardNavigation: true
    });
    $('.queryBtn').on('click',function(){
        loadSchList();
    });



    function loadSchList() {
        var now = new Date();
        var tomorrow = new Date(now.getTime()+24*3600*1000);
        var endTime = dateEnd(tomorrow);
        var yestoday = new Date(now.getTime()-24*3600*1000);
        var startTime = dateStart(yestoday);
        var result = [];
        var param = "?userId=1&startTime=" + startTime + "&endTime=" + endTime;
        $.get(querySchUrl + param, function (r) {
            result = r.data;
            initTable(result);
            bindToolbar();
        });
    }
    function bindToolbar() {
            table.find("tr").each(function(){
                console.log($(this));
                var schId = $(this).attr("schId");
                var finishBtn = $(this).find(".finish");
                finishBtn.on("click",function(){
                   updateSchRcd(1,schId);
                });
                var giveupBtn = $(this).find(".giveup");
                giveupBtn.on("click",function(){
                   updateSchRcd(-1,schId);
                });
            });
    }

    function updateSchRcd(state,id){

    }
    loadSchList();

    function initTable(data) {

        table.grid({
            id:"mainTable",
            data:data,
            columns: [
                {field:'ck',checkbox:true},
                { field: 'id', title: '序号', width:100, align: 'left'},
                { field: 'schedule.title', title: '计划内容', width: 150, align: 'left' },
                { field: 'deadLine', title: '最后期限', width: 50, align: 'left' }
            ],
            isoddcolor:false,
            pagination:false,
            searchnation:false,
            pagesize:20
        });
    }




    $newBtn.on('click', function () {
        $('#detail').modal({
            keyboard: true
        });
    });

    $saveBtn.on('click', function () {
        var title = $form.find('#title');
        var firstDeadLine = $form.find('#firstDeadLine');
        var scheduleNum = $form.find('#scheduleNum');
        var scheduleType = $form.find('input[type="radio"]:checked');
        var remark = $form.find('#remark');
        var userId = $form.find("#userId");
        var data = {
            title: title.val(),
            firstDeadLine: firstDeadLine.data("datetimepicker").getDate().Format("yyyy-MM-dd HH:mm:ss"),
            scheduleNum: scheduleNum.val(),
            scheduleType: scheduleType.val(),
            remark: remark.val(),
            "user.id": userId.val()
        }
        $.post('/sch',
            data
            , function (result) {
                if (result.state == 1) {
                    alert("保存成功！");
                    $("#detail").modal('hide');
                    queryRecords();
                } else {
                    alert("保存失败：", result.message);
                }
            });
    });

    function formatDateTime(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    };

    function formatDate(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    };

    function dateStart(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d + ' 00:00:00';
    };

    function dateEnd(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d + ' 23:59:59';
    };
});